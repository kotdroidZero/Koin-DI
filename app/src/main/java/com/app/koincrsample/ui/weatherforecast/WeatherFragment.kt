package com.app.koincrsample.ui.weatherforecast


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.app.koincrsample.BR
import com.app.koincrsample.R
import com.app.koincrsample.data.model.custom.Status
import com.app.koincrsample.databinding.FragmentWeatherBinding
import com.app.koincrsample.utils.showToast
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

class WeatherFragment : Fragment() {


    private val weatherViewModel: WeatherViewModel by viewModel()
    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set variables
        binding.setVariable(BR.viewModel, weatherViewModel)

        binding.lifecycleOwner = this
        binding.executePendingBindings()

        // observe data
        observeData()

    }

    /**
     * fun to observe all the liveData
     */
    private fun observeData() {
        weatherViewModel.weather.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    weatherViewModel.getLoading().value = false
                    binding.tvWeatherReport.text = ""
                    binding.tvWeatherReport.text =
                        "Temperature at ${it.data?.name} is ${it.data?.temp?.temp} celcius"
                }
                Status.ERROR -> {
                    weatherViewModel.getLoading().value = false
                    showToast(message = it.message)
                }
                Status.LOADING -> {
                    weatherViewModel.getLoading().value = weatherViewModel.getLoading().value?.not()
                }
            }
        })
    }
}
