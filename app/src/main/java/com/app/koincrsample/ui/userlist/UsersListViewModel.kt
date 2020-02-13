package com.app.koincrsample.ui.userlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.koincrsample.base.BaseViewModel
import com.app.koincrsample.data.local.prefs.PrefManager
import com.app.koincrsample.data.model.custom.Resource
import com.app.koincrsample.data.model.response.UserListResponse
import com.app.koincrsample.data.remote.DataRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.dsl.module

/**
 * @AUTHOR Pushkar Srivastava
 * @date 11/02/2020
 */


val usersListViewModelModule = module {
    factory { UsersListViewModel(get(), get()) }
}

class UsersListViewModel(prefsManager: PrefManager, private val dataRepository: DataRepository) :
    BaseViewModel<UserListViewActor>() {

    val userListResponse = MutableLiveData<Resource<UserListResponse>>()


    fun getUsers() {
        viewModelScope.launch {
            getLoading().postValue(true)
            var response: Resource<UserListResponse>? = null
            async {
                response = dataRepository.getUsers()
            }.join()
            response?.let { processResponse(it) }
        }
    }



}