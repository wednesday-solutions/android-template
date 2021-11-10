package com.wednesday.template.presentation.base.toolbar

import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.base.asString
import com.wednesday.template.presentation.base.component.StatefulComponent
import com.wednesday.template.presentation.base.getString

class ToolbarComponent(
    private val fragment: Fragment,
    private val onBackClicked: (() -> Unit) = {
        if (fragment.activity?.onNavigateUp() == false) {
            fragment.activity?.onBackPressed()
        }
    },
    private val onMenuClicked: (() -> Unit)? = null
) : StatefulComponent<UIToolbar>() {

    override fun bindInternal(view: View) = Unit

    override fun setDataInternal(newData: UIToolbar) {
        (fragment.activity as? AppCompatActivity)?.apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(newData.hasBackButton)
            title = fragment.getString(newData.title)
            newData.menuTitle?.let {
                invalidateOptionsMenu()
            }
        }
    }

    override fun unBindInternal() = Unit

    fun onCreateOptionsMenu(menu: Menu) {
        val currentData = currentData
        currentData?.menuTitle?.let {
            menu.add(0, MENU_ITEM_ID, Menu.NONE, it.asString(fragment.requireContext()))
            val menuItem = menu.findItem(MENU_ITEM_ID)
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            menuItem.isEnabled = currentData.menuButtonEnabled
        }
    }

    fun onOptionsItemSelected(itemId: Int) = when (itemId) {
        MENU_ITEM_ID -> onMenuClicked?.invoke()
        else -> onBackClicked()
    }

    companion object {
        private const val MENU_ITEM_ID = 1
    }
}
