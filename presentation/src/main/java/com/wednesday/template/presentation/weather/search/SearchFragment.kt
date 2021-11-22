package com.wednesday.template.presentation.weather.search

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.viewModels
import com.wednesday.template.presentation.base.effect.Effect
import com.wednesday.template.presentation.base.fragment.BaseFragment
import com.wednesday.template.presentation.base.fragment.BindingProvider
import com.wednesday.template.presentation.base.viewmodel.BaseViewModel
import com.wednesday.template.presentation.screen.Screen
import com.wednesday.template.presentation.screen.ScreenState
import com.wednesday.template.resources.databinding.FragmentSearchBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment<
        SCREEN_STATE:ScreenState> : BaseFragment<FragmentSearchBinding,
        SearchFragmentScreen,
        SCREEN_STATE,
        BaseViewModel<SearchFragmentScreen,SCREEN_STATE>>()
{

    val vm:SearchFragmentViewModel by viewModels()

    @CallSuper
    override fun onViewCreated(binding: FragmentSearchBinding) {
        super.onViewCreated(binding)

        setOnClickListener(binding)

    }

    private fun setOnClickListener(binding: FragmentSearchBinding) = with(binding) {

        searchEditText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(name: Editable?) {
                vm.searchCity(name.toString())
            }

        })

    }



    override fun onEffect(effect: Effect) {

    }

    override fun onState(screenState: SCREEN_STATE) {

    }

    override val viewModel: BaseViewModel<SearchFragmentScreen, SCREEN_STATE> by viewModels()

    override val bindingProvider: BindingProvider<FragmentSearchBinding>
        get() = TODO("Not yet implemented")
}
