package com.app.koincrsample.ui.starter

import com.app.koincrsample.BR
import com.app.koincrsample.R
import com.app.koincrsample.base.BaseActivity
import com.app.koincrsample.databinding.ActivityMainBinding
import com.app.koincrsample.ui.userlist.UserListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module


/**
 * @AUTHOR Pushkar Srivastava
 * @date 08/02/2020
 */

/**
 * factory definition creates new instance each times it required
 */
val mainActivityModule = module {
    factory { MainActivity() }
}

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    //region Variables
    private val mainViewModel: MainViewModel by viewModel()
    //endregion

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel
        get() = mainViewModel

    override
    val isMakeStatusBarTransparent: Boolean
        get() = false

    override fun init() {
        supportFragmentManager.beginTransaction().add(R.id.flMainContainer, UserListFragment())
            .commit()
    }
}
