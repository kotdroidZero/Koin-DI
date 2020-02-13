package com.app.koincrsample.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.app.koincrsample.utils.showToast

/**
 * @AUTHOR Pushkar Srivastava
 * @date 12/02/2020
 */
abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<out BaseViewActor>>(@LayoutRes private val layoutResId: Int) :
    Fragment() {


    var baseActivity: BaseActivity<T, V>? = null

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V

    /**
     * Override for set viewDataBinding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    lateinit var viewDataBinding: T

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        // get dataBinding
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutResId, container, false)

        // set dataBinding variables
        viewDataBinding.setVariable(bindingVariable, viewModel)

        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        viewDataBinding.executePendingBindings()

        return viewDataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observeCommonData()
    }

    private fun observeCommonData() {
        // observe common messages
        viewModel.getMessage().observe(viewLifecycleOwner, Observer {
            showToast(message = it)
        })

        observeData()
    }

    abstract fun observeData()

    abstract fun init()


}