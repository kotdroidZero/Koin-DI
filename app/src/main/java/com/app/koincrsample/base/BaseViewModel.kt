package com.app.koincrsample.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.koincrsample.data.model.custom.Resource
import com.app.koincrsample.data.model.custom.Status
import com.app.koincrsample.data.model.response.BaseResponse

/**
 * @AUTHOR Pushkar Srivastava
 * @date 11/02/2020
 */
abstract class BaseViewModel<N:BaseViewActor> : ViewModel() {

    init {
        getLoading().postValue(false)
    }

    private var loading: MutableLiveData<Boolean>? = null
    private var result: MutableLiveData<Resource<out BaseResponse>>? = null
    private var message: MutableLiveData<String>? = null

    fun getLoading(): MutableLiveData<Boolean> {
        if (loading == null) loading = MutableLiveData()
        return loading!!
    }

    fun getMessage(): MutableLiveData<String> {
        if (message == null) message = MutableLiveData()
        return message!!
    }

    fun <T> getResult(): MutableLiveData<Resource<out BaseResponse>> {
        if (result == null) result = MutableLiveData()
        return result!!
    }

    fun <T : BaseResponse> processResponse(response: Resource<T>) {
        when (response.status) {
            Status.SUCCESS -> getResult<BaseResponse>().postValue(response)
            Status.ERROR -> getMessage().postValue(response.message)
        }
        getLoading().postValue(false)
    }


}