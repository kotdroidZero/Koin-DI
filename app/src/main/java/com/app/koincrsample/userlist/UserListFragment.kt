package com.app.koincrsample.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.koincrsample.BR
import com.app.koincrsample.R
import com.app.koincrsample.databinding.FragmentUserListBinding

/**
 * @AUTHOR Pushkar Srivastava
 * @date 12/02/2020
 */
class UserListFragment : Fragment() {

    lateinit var binding: FragmentUserListBinding
    private val mViewModel: UserListViewModel by viewModels()


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


        // set lifeCycleOwner
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        // set binding variables
        binding.setVariable(BR.viewModel, mViewModel)

        // observe data
        observeData()

    }

    /**
     * fun to observe all the liveData
     */
    private fun observeData() {

    }

}