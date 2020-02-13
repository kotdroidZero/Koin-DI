package com.app.koincrsample.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.koincrsample.data.model.custom.Resource
import com.app.koincrsample.data.model.custom.Status
import com.app.koincrsample.data.model.response.BaseTypeSafetyResponse

/**
 * @AUTHOR Pushkar Srivastava
 * @date 11/02/2020
 */
abstract class BaseViewModel : ViewModel() {

    init {
        getLoading().postValue(false)
    }

    private var loading: MutableLiveData<Boolean>? = null

    fun getLoading(): MutableLiveData<Boolean> {
        if (loading == null) loading = MutableLiveData()
        Log.i("really", loading!!.value.toString())
        return loading!!
    }

    fun <T : BaseTypeSafetyResponse> processResponse(resource: Resource<T>) {
        when (resource.status) {
            Status.SUCCESS -> {
                getLoading().value = false
                getResponseTypeLiveData<T>().value = resource
            }
            Status.LOADING -> {
                getLoading().value = getLoading().value?.not()
            }
            Status.ERROR -> {
                getLoading().value = false
                getResponseTypeLiveData<T>().value = resource

            }
        }
    }


    abstract fun <T> getResponseTypeLiveData(): MutableLiveData<Resource<T>>


}