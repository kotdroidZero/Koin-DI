package com.app.koincrsample.userlist

import com.app.koincrsample.base.BaseViewModel
import com.app.koincrsample.data.remote.DataRepository
import org.koin.dsl.module

/**
 * @AUTHOR Pushkar Srivastava
 * @date 11/02/2020
 */


val userListViewModelModule = module {
    factory { UserListViewModel(get()) }
}

class UserListViewModel(
    private val dataRepository: DataRepository
) : BaseViewModel(dataRepository)