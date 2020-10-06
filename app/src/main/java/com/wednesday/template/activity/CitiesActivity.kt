package com.wednesday.template.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.wednesday.template.R
import com.wednesday.template.activity.base.BaseUpActivity

class CitiesActivity : BaseUpActivity() {

  companion object {
    fun newInstance(context: Context): Intent {
      return Intent(context, CitiesActivity::class.java)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_cities)
    supportActionBar?.title = getString(R.string.add_a_city)
  }
}
