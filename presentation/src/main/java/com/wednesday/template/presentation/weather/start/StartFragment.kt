package com.wednesday.template.presentation.weather.start

import android.util.Log
import androidx.fragment.app.viewModels
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
    
    override val toolbarComponent: ToolbarComponent = ToolbarComponent(this)

    override val viewModel: StartScreenViewModel by navViewModel()
    
    override val bindingProvider: BindingProvider<FragmentStartBinding> = FragmentStartBinding::inflate

    override fun onEffect(effect: Effect) {
    }
    
    override fun onViewCreated(binding: FragmentStartBinding) {
        super.onViewCreated(binding)
        Log.d("*************","Calledddddddddddddddd")
        initializeList(binding)
    }
    
    private fun initializeList(binding: FragmentStartBinding) = with(binding) {
    
    }
    
    override fun onState(screenState: StartScreenState) {
        super.onState(screenState)
    }

}
