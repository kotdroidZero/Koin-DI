package com.app.koincrsample.ui.weather


import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import com.app.koincrsample.BR
import com.app.koincrsample.R
import com.app.koincrsample.base.BaseFragment
import com.app.koincrsample.data.model.custom.Resource
import com.app.koincrsample.data.model.response.BaseResponse
import com.app.koincrsample.data.model.response.Weather
import com.app.koincrsample.databinding.FragmentWeatherBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

/**
 * @AUTHOR Pushkar Srivastava
 * @date 11/02/2020
 */

/**
 * factory definition creates new instance each times it required
 */
val weatherFragmentModule = module {
    factory { WeatherFragment() }
}

class WeatherFragment :
    BaseFragment<FragmentWeatherBinding, WeatherViewModel>(R.layout.fragment_weather),
    WeatherViewActor {

    //region Variables
    private val weatherViewModel: WeatherViewModel by viewModel()
    //endregion

    //region BaseClass Methods
    override val viewModel: WeatherViewModel
        get() = weatherViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun init() {

    }

    /**
     * fun to observe all the liveData
     */
    @SuppressLint("SetTextI18n")
    override fun observeData() {

        // observe any kind of data
        weatherViewModel.getResult<Resource<BaseResponse>>()
            .observe(viewLifecycleOwner, Observer { data ->

                when (data.data) {
                    is Weather -> {
                        viewDataBinding.tvWeatherReport.text = ""
                        viewDataBinding.tvWeatherReport.text =
                            "Temperature at ${data.data.name} is ${data.data.temp.temp} celsius"
                    }
                }
            })


        // observe data using flow
        viewModel.weatherForecast?.observe(viewLifecycleOwner, Observer {


            viewDataBinding.btnGetTempUsingFlow.text = ""
            viewDataBinding.btnGetTempUsingFlow.text = it.toString()
        })
    }
    //endregion

}
