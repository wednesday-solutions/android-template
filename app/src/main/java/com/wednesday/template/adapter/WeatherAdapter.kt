package com.wednesday.template.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wednesday.template.R
import com.wednesday.template.model.Weather

class WeatherAdapter(val context: Context, var weatherDataSource: MutableList<Weather>): RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

  class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val city: TextView = itemView.findViewById(R.id.city)
    val minTemp: TextView = itemView.findViewById(R.id.minTemp)
    val maxTemp: TextView = itemView.findViewById(R.id.maxTemp)
    val weatherImage: ImageView = itemView.findViewById(R.id.weatherImage)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.partial_weather_row, parent, false)
    return WeatherAdapter.ViewHolder(view)
  }

  override fun getItemCount(): Int {
    return if (weatherDataSource.isNotEmpty()) weatherDataSource.size else 0
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val weather = weatherDataSource[position]
    holder.city.text = weather.title
    holder.minTemp.text = context.resources.getString(R.string.min_temp, weather.consolidatedWeathers[0].minTemp.roundToInt())
    holder.maxTemp.text = context.resources.getString(R.string.max_temp, weather.consolidatedWeathers[0].maxTemp.roundToInt())
    holder.weatherImage.setImageResource(weather.consolidatedWeathers[0].weatherConditionImageResourceId)
  }

  fun resetDataSource(weather: List<Weather>) {
    weatherDataSource.clear()
    weatherDataSource.addAll(weather)
    notifyDataSetChanged()
  }
}
