package com.app.koincrsample.ui.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import androidx.lifecycle.Observer
import com.app.koincrsample.BR
import com.app.koincrsample.R
import com.app.koincrsample.data.model.custom.Status
import com.app.koincrsample.databinding.FragmentUserListBinding
import com.app.koincrsample.utils.showToast
import kotlinx.android.synthetic.main.fragment_user_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

/**
 * @AUTHOR Pushkar Srivastava
 * @date 12/02/2020
 */


/**
 * factory definition creates new instance each times it required
 */
val userListFragmentModule = module {
    factory { UserListFragment() }
}

class UserListFragment : Fragment() {

    private val mViewModel: UsersListViewModel by viewModel()
    private lateinit var binding: FragmentUserListBinding
    private val mAdapter by lazy { UserListAdapter(mViewModel) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set binding variables
        binding.setVariable(BR.viewModel, mViewModel)

        // set lifeCycleOwner
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        // set adapter
        recyclerView.adapter = mAdapter

        // observe data
        observeData()
    }

    /**
     * fun to observe all the liveData
     */
    private fun observeData() {

        // observe user list reponse
        mViewModel.userListResponse.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    mViewModel.getLoading().value = false
                    mAdapter.updateList(it.data?.user ?: emptyList())
                }
                Status.ERROR -> {
                    mViewModel.getLoading().value = false
                    showToast(message = it.message)
                }
                Status.LOADING -> {
                    mViewModel.getLoading().value = mViewModel.getLoading().value?.not()
                }
            }
        })
    }

}