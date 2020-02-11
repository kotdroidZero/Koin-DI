package com.app.koincrsample.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.koincrsample.data.remote.DataRepository

/**
 * @AUTHOR Pushkar Srivastava
 * @date 11/02/2020
 */
abstract class BaseViewModel(
    private val weatherRepository: DataRepository
) : ViewModel() {

    init {
        getLoading().postValue(false)
    }

    private var loading: MutableLiveData<Boolean>? = null

    fun getLoading(): MutableLiveData<Boolean> {
        if (loading == null) loading = MutableLiveData()
        Log.i("really", loading!!.value.toString())
        return loading!!
    }


}