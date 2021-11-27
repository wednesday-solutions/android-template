package com.wednesday.template.presentation.weather.home

import com.wednesday.template.presentation.base.effect.Effect
import com.wednesday.template.presentation.base.fragment.BindingProvider
import com.wednesday.template.presentation.base.fragment.MainFragment
import com.wednesday.template.presentation.base.list.ListComponent
import com.wednesday.template.presentation.base.toolbar.ToolbarComponent
import com.wednesday.template.presentation.weather.home.list.UISearchCitiesPlaceholderRenderer
import com.wednesday.template.presentation.weather.home.list.UIWeatherRenderer
import com.wednesday.template.resources.R
import com.wednesday.template.resources.databinding.FragmentStartBinding

class HomeFragment : MainFragment<
    FragmentStartBinding,
    HomeScreen,
    HomeScreenState,
    HomeViewModel>() {

    override val toolbarComponent: ToolbarComponent = ToolbarComponent(this) {
        viewModel.onIntent(HomeScreenIntent.Search)
    }

    override val viewModel: HomeViewModel by navViewModel()

    override val bindingProvider: BindingProvider<FragmentStartBinding> =
        FragmentStartBinding::inflate

    private val listComponent by component {
        ListComponent(viewModel, R.id.recyclerView) {
            addRenderer(UISearchCitiesPlaceholderRenderer())
            addRenderer(UIWeatherRenderer())
        }
    }

    override fun onState(screenState: HomeScreenState) {
        super.onState(screenState)
        listComponent.setData(screenState.items)
    }

    override fun onEffect(effect: Effect) {
    }
}
