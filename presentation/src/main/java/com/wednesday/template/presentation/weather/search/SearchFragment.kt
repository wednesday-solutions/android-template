package com.wednesday.template.presentation.weather.search

import androidx.core.widget.addTextChangedListener
import com.wednesday.template.presentation.R
import com.wednesday.template.presentation.base.effect.Effect
import com.wednesday.template.presentation.base.fragment.BindingProvider
import com.wednesday.template.presentation.base.fragment.MainFragment
import com.wednesday.template.presentation.base.list.ListComponent
import com.wednesday.template.presentation.base.toolbar.ToolbarComponent
import com.wednesday.template.presentation.weather.search.list.UICityListRenderer
import com.wednesday.template.resources.databinding.FragmentSearchBinding

class SearchFragment : MainFragment< FragmentSearchBinding,
    SearchScreen,
    SearchScreenState,
    SearchFragmentViewModel >() {

    override val toolbarComponent: ToolbarComponent = ToolbarComponent(this, onBackClicked = {
        viewModel.onIntent(SearchScreenIntent.Back)
    })

    override val viewModel: SearchFragmentViewModel by navViewModel()

    override val bindingProvider: BindingProvider<FragmentSearchBinding> =
        FragmentSearchBinding::inflate

    private val listComponent by component {
        ListComponent(listViewModel = viewModel, recyclerViewId = R.id.searchRecyclerView) {
            addRenderer(UICityListRenderer())
        }
    }

    override fun onViewCreated(binding: FragmentSearchBinding) {
        super.onViewCreated(binding)

        addTextListener(binding)
    }

    override fun onEffect(effect: Effect) {
    }

    override fun onState(screenState: SearchScreenState) {
        super.onState(screenState)
        listComponent.setData(screenState.searchList)
    }

    private fun addTextListener(binding: FragmentSearchBinding) = with(binding) {

        searchEditText.addTextChangedListener {
            it?.let { viewModel.onIntent(SearchScreenIntent.SearchCities(it.toString())) }
        }
    }
}
