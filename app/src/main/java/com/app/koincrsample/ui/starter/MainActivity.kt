package com.app.koincrsample.ui.starter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.koincrsample.R
import com.app.koincrsample.ui.userlist.UserListFragment
import com.app.koincrsample.ui.weatherforecast.WeatherFragment


/**
 * @AUTHOR Pushkar Srivastava
 * @date 08/02/2020
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(
            R.id.flMainContainer,
            UserListFragment()
        )
            .commit()
    }
}
