package com.wednesday.template.activity.base

open class BaseUpActivity : BaseActivity() {
    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
