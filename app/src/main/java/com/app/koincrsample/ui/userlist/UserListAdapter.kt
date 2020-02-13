package com.app.koincrsample.ui.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.app.koincrsample.BR
import com.app.koincrsample.R
import com.app.koincrsample.data.model.response.UserListResponse

/**
 * @AUTHOR Pushkar Srivastava
 * @date 12/02/2020
 */
class UserListAdapter(private val userListViewModel: UsersListViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mUserList = mutableListOf<UserListResponse.User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.row_layout_user_view,
            parent,
            false
        )
        return ViewHolder(binding, userListViewModel)
    }

    override fun getItemCount(): Int = mUserList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bindData(mUserList[position])
    }

    fun updateList(list: List<UserListResponse.User>) {
        mUserList.clear()
        mUserList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ViewDataBinding,
        private val mViewModel: UsersListViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(user: UserListResponse.User) {
            binding.setVariable(BR.viewModel, mViewModel)
            binding.setVariable(BR.user, user)
            binding.executePendingBindings()
        }
    }
}
