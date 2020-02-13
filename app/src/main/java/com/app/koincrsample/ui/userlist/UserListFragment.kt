package com.app.koincrsample.ui.userlist

import androidx.lifecycle.Observer
import com.app.koincrsample.BR
import com.app.koincrsample.R
import com.app.koincrsample.base.BaseFragment
import com.app.koincrsample.data.model.response.UserListResponse
import com.app.koincrsample.databinding.FragmentUserListBinding
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

class UserListFragment :
    BaseFragment<FragmentUserListBinding, UsersListViewModel>(R.layout.fragment_user_list),
    UserListViewActor {

    private val mViewModel: UsersListViewModel by viewModel()
    private val mAdapter by lazy { UserListAdapter(mViewModel) }

    override val viewModel: UsersListViewModel
        get() = mViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun init() {
        // set adapter
        recyclerView.adapter = mAdapter

        // observe data
        observeData()
    }


    /**
     * fun to observe all the liveData
     */
    override fun observeData() {


        mViewModel.getResult<UserListResponse>().observe(viewLifecycleOwner, Observer {
            when (it.data) {
                is UserListResponse -> {
                    mAdapter.updateList(it.data.user)
                }
            }
        })
    }

}