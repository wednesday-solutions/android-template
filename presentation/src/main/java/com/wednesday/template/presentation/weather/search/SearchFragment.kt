package com.wednesday.template.presentation.weather.search

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.wednesday.template.presentation.base.effect.Effect
import com.wednesday.template.presentation.base.fragment.BindingProvider
import com.wednesday.template.presentation.base.fragment.MainFragment
import com.wednesday.template.presentation.base.toolbar.ToolbarComponent
import com.wednesday.template.resources.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import timber.log.Timber

class SearchFragment : MainFragment<FragmentSearchBinding,
        SearchFragmentScreen,
        SearchFragmentScreenState,
        SearchFragmentViewModel>()
{
    override val toolbarComponent: ToolbarComponent = ToolbarComponent(this)

    override val viewModel: SearchFragmentViewModel by stateViewModel()

    override val bindingProvider: BindingProvider<FragmentSearchBinding> = FragmentSearchBinding::inflate

    override fun onViewCreated(binding: FragmentSearchBinding) {
        super.onViewCreated(binding)

        setOnClickListener(binding)
        liveDataObserver()
    }

    private fun liveDataObserver() {
        viewModel.result.observe(viewLifecycleOwner,{
            Log.d("$$$$$$$$$$$4",viewModel.result.value.toString())
        })
    }

    override fun onEffect(effect: Effect) {

    }

    override fun onState(screenState: SearchFragmentScreenState) {
        super.onState(screenState)

    }

    private fun setOnClickListener(binding: FragmentSearchBinding) = with(binding) {

        searchEditText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(name: Editable?) {
                viewModel.onIntent(SearchScreenIntent.SearchCities(name.toString()))

            }

        })

    }



}
