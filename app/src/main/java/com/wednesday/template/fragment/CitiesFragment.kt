package com.wednesday.template.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.wednesday.template.R
import com.wednesday.template.adapter.CitiesAdapter
import com.wednesday.template.adapter.CitySelected
import com.wednesday.template.service.weather.room.DatabaseDao
import com.wednesday.template.model.City
import com.wednesday.template.service.WeatherApiService
import com.wednesday.template.util.addProgressIndicator
import com.wednesday.template.util.removeProgressIndicator
import kotlinx.android.synthetic.main.fragment_cities.*
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance

class CitiesFragment: Fragment(), DIAware, CitySelected {

  override val di: DI by di()
  private val apiService: com.wednesday.template.service.WeatherApiService by instance("apiService")
  private val databaseDao: com.wednesday.template.service.weather.room.DatabaseDao by instance("databaseDao")

  private val citiesAdapter: CitiesAdapter by lazy {
    CitiesAdapter(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_cities, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    databaseDao.getObservableFavoriteCities().observe(viewLifecycleOwner, Observer { cities ->
      citiesAdapter.setFavoriteCities(cities)
    })
    searchCityEditText.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

      }

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s.isNullOrBlank()) {
          citiesRecyclerView.visibility = View.GONE
        } else {
          fetchCities(s.toString())
        }
      }

      override fun afterTextChanged(s: Editable?) {
      }

    })

    val linearLayoutManager = LinearLayoutManager(context)
    citiesRecyclerView.layoutManager = linearLayoutManager
    citiesRecyclerView.adapter = citiesAdapter
  }

  private fun fetchCities(searchText: String) {
    showLoading(true)

    viewLifecycleOwner.lifecycleScope.launch {
      val cities = apiService.searchCities(searchText)
      citiesAdapter.resetDataSource(cities)
      showLoading(false)
    }
  }

  private fun showLoading(loading: Boolean) {
    val rootView = searchCityResultHolder
    if (loading) {
      citiesRecyclerView.visibility = View.GONE
      addProgressIndicator(requireContext(), rootView)
    } else {
      removeProgressIndicator(rootView)
      citiesRecyclerView.visibility = View.VISIBLE
    }
  }

  override fun onCitySelected(city: City) {
    viewLifecycleOwner.lifecycleScope.launch {
      databaseDao.markCityAsFavorite(city)
    }
  }
}
