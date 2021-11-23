package com.wednesday.template.presentation.weather.search

import androidx.core.widget.addTextChangedListener
import com.wednesday.template.presentation.R
import com.wednesday.template.presentation.base.effect.Effect
import com.wednesday.template.presentation.base.fragment.BindingProvider
import com.wednesday.template.presentation.base.fragment.MainFragment
import com.wednesday.template.presentation.base.list.ListComponent
import com.wednesday.template.presentation.base.toolbar.ToolbarComponent
import com.wednesday.template.resources.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class SearchFragment : MainFragment<FragmentSearchBinding,
    SearchFragmentScreen,
    SearchFragmentScreenState,
    SearchFragmentViewModel>() {
    override val toolbarComponent: ToolbarComponent = ToolbarComponent(this)

    override val viewModel: SearchFragmentViewModel by stateViewModel()

    override val bindingProvider: BindingProvider<FragmentSearchBinding> =
        FragmentSearchBinding::inflate

    private val listComponent by component {
        ListComponent(listViewModel = viewModel, recyclerViewId = R.id.searchRecyclerView) {
            addRenderer(UICityListRenderer())
        }
    }

    override fun onViewCreated(binding: FragmentSearchBinding) {
        super.onViewCreated(binding)
        textWatcher(binding)
    }

    override fun onEffect(effect: Effect) {
    }

    override fun onState(screenState: SearchFragmentScreenState) {
        super.onState(screenState)
        listComponent.setData(screenState.searchList)
    }

    private fun textWatcher(binding: FragmentSearchBinding) = with(binding) {

        searchEditText.addTextChangedListener {
            it?.let { viewModel.onIntent(SearchScreenIntent.SearchCities(it.toString())) }
        }
    }
}
