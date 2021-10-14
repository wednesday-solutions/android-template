package com.wednesday.template.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wednesday.template.R
import com.wednesday.template.adapter.WeatherAdapter
import com.wednesday.template.model.Status
import com.wednesday.template.model.Weather
import com.wednesday.template.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.partial_toolbar.*

class WeatherFragment: Fragment() {
  private lateinit var viewModel: WeatherViewModel
  private var callback: WeatherLoading? = null

  interface WeatherLoading {
    fun showLoadingIndicator(showLoading: Boolean)
  }

  private val weatherAdapter: WeatherAdapter by lazy {
    WeatherAdapter(context!!, emptyList<Weather>().toMutableList())
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_weather, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    val linearLayoutManager = LinearLayoutManager(context)
    weatherRecyclerView.layoutManager = linearLayoutManager

    val dividerItemDecoration = DividerItemDecoration(
      weatherRecyclerView.context,
      linearLayoutManager.orientation
    )
    weatherRecyclerView.addItemDecoration(dividerItemDecoration)

    weatherRecyclerView.adapter = weatherAdapter
    viewModel = ViewModelProvider(
      this,
      ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)).get(WeatherViewModel::class.java)
    viewModel.weatherLiveData.observe(this, Observer{ weathersResource ->
      when(weathersResource.status) {
        Status.Success -> {
          callback?.showLoadingIndicator(false)
          weatherAdapter.resetDataSource(weathersResource.data ?: listOf())
        }
        Status.Loading -> {
          callback?.showLoadingIndicator(true)
          weatherAdapter.resetDataSource(weathersResource.data ?: listOf())
        }
        Status.Error -> TODO()
      }
    })
  }

  override fun onResume() {
    super.onResume()
    viewModel.triggerLoadForAllFavoriteCities()
  }

  fun setWeatherLoadingDelegate(weatherLoadingDelegate: WeatherLoading) {
    this.callback = weatherLoadingDelegate
  }
}
