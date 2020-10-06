package com.wednesday.template.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.wednesday.template.R
import com.wednesday.template.activity.base.BaseActivity
import com.wednesday.template.fragment.WeatherFragment
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.partial_toolbar.*

class WeatherActivity : BaseActivity(), WeatherFragment.WeatherLoading {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_weather)
    supportActionBar?.title = getString(R.string.weather)

    addFavoriteWeatherActionButton.setOnClickListener { _ ->
      startActivity(CitiesActivity.newInstance(this))
    }
  }

  override fun onAttachFragment(fragment: Fragment) {
    if (fragment is WeatherFragment) {
      fragment.setWeatherLoadingDelegate(this)
    }
  }
  override fun showLoadingIndicator(showLoading: Boolean) {
    toolbarProgressIndicator.visibility = if (showLoading) View.VISIBLE else View.INVISIBLE
  }
}
