package com.wednesday.template.activity.base

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.partial_toolbar.*

open class BaseActivity : AppCompatActivity() {
    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        setSupportActionBar(toolbar)
    }
}
