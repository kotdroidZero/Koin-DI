package com.app.koincrsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.koincrsample.weatherforecast.WeatherFragment


/**
 * @AUTHOR Pushkar Srivastava
 * @date 08/02/2020
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.flMainContainer, WeatherFragment())
            .commit()
    }
}
