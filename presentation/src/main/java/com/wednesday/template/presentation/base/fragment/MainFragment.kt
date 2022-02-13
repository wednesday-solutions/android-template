package com.wednesday.template.presentation.base.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import com.wednesday.template.navigation.Navigator
import com.wednesday.template.presentation.base.component.StatefulComponent
import com.wednesday.template.presentation.base.loading.LoadingComponent
import com.wednesday.template.presentation.base.toolbar.ToolbarComponent
import com.wednesday.template.presentation.base.viewmodel.BaseViewModel
import com.wednesday.template.presentation.screen.MainScreenState
import com.wednesday.template.presentation.screen.Screen

abstract class MainFragment<
    BINDING : ViewBinding,
    SCREEN : Screen,
    SCREEN_STATE : MainScreenState,
    NAV : Navigator,
    VM : BaseViewModel<SCREEN, SCREEN_STATE>
    > : BaseFragment<BINDING, SCREEN, SCREEN_STATE, NAV, VM>() {

    abstract val toolbarComponent: ToolbarComponent

    private val internalToolbarComponent by component { toolbarComponent }
    private val loadingComponent by component { LoadingComponent() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @CallSuper
    override fun onViewCreated(binding: BINDING) {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if ((loadingComponent as StatefulComponent).currentData == true) return
                    if (!onBackPressed()) {
                        isEnabled = false
                        activity?.onBackPressed()
                    }
                }
            }
        )
    }

    @CallSuper
    override fun onState(screenState: SCREEN_STATE) {
        internalToolbarComponent.setData(screenState.toolbar)
        loadingComponent.setData(screenState.showLoading)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (internalToolbarComponent as? ToolbarComponent)?.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if ((loadingComponent as StatefulComponent).currentData == true) return true
        toolbarComponent.onOptionsItemSelected(item.itemId)
        return true
    }
}
