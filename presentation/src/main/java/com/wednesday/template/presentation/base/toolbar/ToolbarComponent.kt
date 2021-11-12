package com.wednesday.template.presentation.base.toolbar

import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.wednesday.template.presentation.R
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.base.asString
import com.wednesday.template.presentation.base.component.StatefulComponent

class ToolbarComponent(
    private val fragment: Fragment,
    private val onBackClicked: (() -> Unit) = {
        if (fragment.activity?.onNavigateUp() == false) {
            fragment.activity?.onBackPressed()
        }
    },
    private val onExitClicked: (() -> Unit)? = null
) : StatefulComponent<UIToolbar>() {

    override fun bindInternal(view: View) = Unit

    override fun setDataInternal(newData: UIToolbar) {
        (fragment.activity as? AppCompatActivity)?.apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(newData.hasBackButton)
            title = newData.title.asString(this)
            if (newData.menuTitle != null) {
                invalidateOptionsMenu()
            }
        }
    }

    override fun unBindInternal() = Unit

    fun onCreateOptionsMenu(menu: Menu) {
        val currentData = currentData
        if (currentData?.menuButtonEnabled == true) {
            // todo Add menu item
            val menuItemWithIcon =
                menu.add(0, MENU_ITEM_ID, Menu.NONE, "")
            val menuItem = menu.findItem(MENU_ITEM_ID)
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        }
    }

    fun onOptionsItemSelected(itemId: Int) = when (itemId) {
        MENU_ITEM_ID -> onExitClicked?.invoke()
        else -> onBackClicked()
    }

    companion object {
        private const val MENU_ITEM_ID = 1
    }
}
