package com.wednesday.template.presentation.weather.start

import com.wednesday.template.presentation.base.effect.Effect
import com.wednesday.template.presentation.base.fragment.BindingProvider
import com.wednesday.template.presentation.base.fragment.MainFragment
import com.wednesday.template.presentation.base.toolbar.ToolbarComponent
import com.wednesday.template.resources.databinding.FragmentStartBinding

class StartFragment : MainFragment<
    FragmentStartBinding,
    StartFragmentScreen,
    StartScreenState,
    StartScreenViewModel>() {

    override val viewModel: StartScreenViewModel
        get() = TODO("Not yet implemented")
    override val bindingProvider: BindingProvider<FragmentStartBinding>
        get() = TODO("Not yet implemented")

    override fun onEffect(effect: Effect) {
        TODO("Not yet implemented")
    }

    override val toolbarComponent: ToolbarComponent
        get() = TODO("Not yet implemented")
}
