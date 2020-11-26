package com.wednesday.template.fragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.wednesday.template.R
import com.wednesday.template.adapter.CitiesAdapter
import com.wednesday.template.adapter.CitySelected
import com.wednesday.template.database.DatabaseDao
import com.wednesday.template.model.City
import com.wednesday.template.network.WeatherApiService
import com.wednesday.template.util.addProgressIndicator
import com.wednesday.template.util.hideKeyboard
import com.wednesday.template.util.removeProgressIndicator
import kotlinx.android.synthetic.main.fragment_cities.*
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance

class CitiesFragment : Fragment(), DIAware, CitySelected {

    override val di: DI by di()
    private val apiService: WeatherApiService by instance("apiService")
    private val databaseDao: DatabaseDao by instance("databaseDao")

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

        databaseDao.getObservableFavoriteCities().observe(
            this@CitiesFragment,
            Observer { cities ->
                citiesAdapter.setFavoriteCities(cities)
            }
        )
        searchCityEditText.setOnKeyListener { _, _, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                activity?.let { hideKeyboard(it) }
                fetchCities(searchCityEditText.text.toString())
                true
            } else {
                false
            }
        }

        val linearLayoutManager = LinearLayoutManager(context)
        citiesRecyclerView.layoutManager = linearLayoutManager
        citiesRecyclerView.adapter = citiesAdapter
    }

    private fun fetchCities(searchText: String) {
        val rootView = view as ConstraintLayout
        addProgressIndicator(context!!, rootView)

        viewLifecycleOwner.lifecycleScope.launch {
            val cities = apiService.searchCities(searchText)
            removeProgressIndicator(rootView)
            citiesAdapter.resetDataSource(cities)
        }
    }

    override fun onCitySelected(city: City) {
        viewLifecycleOwner.lifecycleScope.launch {
            databaseDao.markCityAsFavorite(city)
        }
    }
}
