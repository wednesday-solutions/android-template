package com.wednesday.template.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wednesday.template.R
import com.wednesday.template.model.City

interface CitySelected {
  fun onCitySelected(city: City)
}

class CitiesAdapter(private val citySelectedDelegate: CitySelected? = null): RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

  private var cities: List<City> = listOf()
  private var favoriteCities: List<City> = listOf()

  class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val city: TextView = itemView.findViewById(R.id.city)
    val checkmark: ImageView = itemView.findViewById(R.id.checkmark)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.partial_city_row, parent, false)
    return ViewHolder(view)
  }

  override fun getItemCount(): Int {
    return if (cities.isNotEmpty()) cities.size else 0
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val city = cities[position]
    val isFavorite = favoriteCities.contains(city)
    holder.checkmark.visibility = if(isFavorite) View.VISIBLE else View.INVISIBLE
    holder.city.text = city.title
    if (citySelectedDelegate != null) {
      holder.itemView.setOnClickListener {
        citySelectedDelegate.onCitySelected(city)
      }
    }
  }

  fun setFavoriteCities(favoriteCities: List<City>) {
    this.favoriteCities = favoriteCities
    notifyDataSetChanged()
  }

  fun resetDataSource(cities: List<City>) {
    this.cities = cities
    notifyDataSetChanged()
  }
}
