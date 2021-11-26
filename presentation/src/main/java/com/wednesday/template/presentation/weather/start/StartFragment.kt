package com.wednesday.template.presentation.weather.start

import com.wednesday.template.presentation.base.effect.Effect
import com.wednesday.template.presentation.base.fragment.BindingProvider
import com.wednesday.template.presentation.base.fragment.MainFragment
import com.wednesday.template.presentation.base.list.ListComponent
import com.wednesday.template.presentation.base.toolbar.ToolbarComponent
import com.wednesday.template.presentation.weather.search.SearchFragmentScreen
import com.wednesday.template.presentation.weather.start.list.FavoriteCityListRenderer
import com.wednesday.template.resources.R
import com.wednesday.template.resources.databinding.FragmentStartBinding

class StartFragment : MainFragment<
    FragmentStartBinding,
    StartFragmentScreen,
    StartScreenState,
    StartScreenViewModel>() {

    override val toolbarComponent: ToolbarComponent = ToolbarComponent(this)

    override val viewModel: StartScreenViewModel by navViewModel()

    override val bindingProvider: BindingProvider<FragmentStartBinding> = FragmentStartBinding::inflate

    override fun onEffect(effect: Effect) {
    }

    override fun onViewCreated(binding: FragmentStartBinding) {
        super.onViewCreated(binding)
        setOnClickListener(binding)
    }

    private fun setOnClickListener(binding: FragmentStartBinding) = with(binding) {
        searchCityButton.setOnClickListener {
            viewModel.onIntent(StartScreenIntent.OnClickSearchCities(SearchFragmentScreen))
        }
    }

    private val listComponent by component {
        ListComponent(viewModel, R.id.recyclerView) {
            addRenderer(FavoriteCityListRenderer())
        }
    }

    override fun onState(screenState: StartScreenState) {
        super.onState(screenState)
        listComponent.setData(screenState.favoriteCitiesList)
    }
}
