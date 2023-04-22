package com.wednesday.template.presentation.base.toolbar

import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.wednesday.template.presentation.R
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.base.component.StatefulComponent
import com.wednesday.template.presentation.base.extensions.asString

class ToolbarComponent(
    private val fragment: Fragment,
    private val onBackClicked: (() -> Unit) = {
        if (fragment.activity?.onNavigateUp() == false) {
            fragment.activity?.onBackPressed()
        }
    },
    private val onIconClicked: (() -> Unit)? = null
) : StatefulComponent<UIToolbar>() {

    override fun bindInternal(view: View) {
        (fragment.requireActivity() as? AppCompatActivity)?.apply {
            val toolbar: MaterialToolbar? = findViewById(R.id.toolbar)
            if (toolbar != null) {
                setSupportActionBar(toolbar)
            }
        }
    }

    override fun setDataInternal(newData: UIToolbar) {
        (fragment.requireActivity() as? AppCompatActivity)?.apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(newData.hasBackButton)
            supportActionBar?.title = newData.title.asString()
            if (newData.menuIcon != null) {
                invalidateOptionsMenu()
            }
        }
    }

    override fun unBindInternal() = Unit

    fun onCreateOptionsMenu(menu: Menu) {
        val currentData = currentData
        currentData?.menuIcon?.let {
            menu.add(0, MENU_ITEM_ID, Menu.NONE, "").apply {
                icon = ContextCompat.getDrawable(fragment.requireContext(), it)
            }
            val menuItem = menu.findItem(MENU_ITEM_ID)
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            menuItem.isEnabled = currentData.menuButtonEnabled
        }
    }

    fun onOptionsItemSelected(itemId: Int) = when (itemId) {
        MENU_ITEM_ID -> onIconClicked?.invoke()
        else -> onBackClicked()
    }

    companion object {
        private const val MENU_ITEM_ID = 1
    }
}
