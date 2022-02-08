package com.wednesday.template.presentation.weather.search

import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import com.wednesday.template.navigation.search.SearchNavigator
import com.wednesday.template.presentation.R
import com.wednesday.template.presentation.base.common.HideKeyboardComponent
import com.wednesday.template.presentation.base.effect.Effect
import com.wednesday.template.presentation.base.effect.HideKeyboardEffect
import com.wednesday.template.presentation.base.effect.ShowSnackbarEffect
import com.wednesday.template.presentation.base.fragment.BindingProvider
import com.wednesday.template.presentation.base.fragment.MainFragment
import com.wednesday.template.presentation.base.list.ListComponent
import com.wednesday.template.presentation.base.snackbar.SnackbarComponent
import com.wednesday.template.presentation.base.toolbar.ToolbarComponent
import com.wednesday.template.presentation.weather.search.list.UICityListRenderer
import com.wednesday.template.resources.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SearchFragment : MainFragment<FragmentSearchBinding,
    SearchScreen,
    SearchScreenState,
    SearchNavigator,
    SearchViewModel>() {

    override val toolbarComponent: ToolbarComponent = ToolbarComponent(this, onBackClicked = {
        viewModel.onIntent(SearchScreenIntent.Back)
    })

    override val viewModel: SearchViewModel by viewModel()

    override val navigator: SearchNavigator by navigator()

    override val bindingProvider: BindingProvider<FragmentSearchBinding> =
        FragmentSearchBinding::inflate

    private val listComponent by component {
        ListComponent(listViewModel = viewModel, recyclerViewId = R.id.searchRecyclerView) {
            addRenderer(UICityListRenderer())
        }
    }
    private val snackbarComponent by component {
        SnackbarComponent(this)
    }

    private val hideKeyboardComponent by component {
        HideKeyboardComponent(requireActivity())
    }

    override fun onViewCreated(binding: FragmentSearchBinding) {
        super.onViewCreated(binding)

        addTextListener(binding)
    }

    override fun onEffect(effect: Effect) {
        when (effect) {
            is HideKeyboardEffect -> hideKeyboardComponent.setData(effect)
            is ShowSnackbarEffect -> snackbarComponent.setData(effect)
        }
    }

    override fun onState(screenState: SearchScreenState) {
        super.onState(screenState)
        listComponent.setData(screenState.searchList)
    }

    private fun addTextListener(binding: FragmentSearchBinding) = with(binding) {

        searchEditText.addTextChangedListener {
            it?.let { viewModel.onIntent(SearchScreenIntent.SearchCities(it.toString())) }
        }

        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onEffect(HideKeyboardEffect)
                Timber.d("User Completed Search")
            }
            false
        }
    }
}
