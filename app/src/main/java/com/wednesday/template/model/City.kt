package com.wednesday.template.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite_cities")
data class City(
  @PrimaryKey
  val woeid: Int,

  @SerializedName("title")
  val title: String,

  @SerializedName("location_type")
  val locationType: String
)
