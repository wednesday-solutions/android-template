package com.wednesday.template.presentation.base.fragment

import android.content.ComponentCallbacks
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.wednesday.template.navigation.Navigator
import com.wednesday.template.presentation.base.component.Component
import com.wednesday.template.presentation.base.effect.Effect
import com.wednesday.template.presentation.base.viewmodel.BaseViewModel
import com.wednesday.template.presentation.screen.Screen
import com.wednesday.template.presentation.screen.ScreenState
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

typealias BindingProvider<B> = (LayoutInflater, ViewGroup?, Boolean) -> B

abstract class BaseFragment<
    BINDING : ViewBinding,
    SCREEN : Screen,
    SCREEN_STATE : ScreenState,
    NAV : Navigator,
    VM : BaseViewModel<SCREEN, SCREEN_STATE, NAV>
    > : Fragment() {

    abstract val viewModel: VM

    abstract val navigator: NAV

    protected lateinit var args: SCREEN

    protected abstract val bindingProvider: BindingProvider<BINDING>

    private var _binding: BINDING? = null
    protected val binding: BINDING?
        get() = _binding

    abstract fun onState(screenState: SCREEN_STATE)

    abstract fun onEffect(effect: Effect)

    open fun onBackPressed(): Boolean = false

    private val components = mutableListOf<Lazy<Component<*>>>()

    open fun onViewCreated(binding: BINDING) = Unit

    @Suppress("UNCHECKED_CAST")
    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = arguments?.get("key_args") as SCREEN
        viewModel.args = args
        viewModel.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return bindingProvider(inflater, container, false).let {
            _binding = it
            it.root
        }
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        components.forEach { it.value.bind(view) }
        binding?.let { onViewCreated(it) }
        viewModel.screenState.observe(viewLifecycleOwner) {
            it ?: return@observe
            onState(it)
        }
        viewModel.effectState.observe(viewLifecycleOwner) {
            it ?: return@observe
            onEffect(it)
        }
        viewModel.screenResultState.observe(viewLifecycleOwner) {
            it ?: return@observe
            setFragmentResult(it)
        }
    }

    override fun onDestroyView() {
        components.forEach { it.value.unbind() }
        _binding = null
        viewModel.onDestroyView()
        super.onDestroyView()
    }

    protected fun <T> component(provider: () -> Component<T>): Lazy<Component<T>> {
        return lazy(provider).apply {
            components.add(this)
        }
    }

    protected inline fun <reified TARGET : Screen> setFragmentResultListener(crossinline callback: (Bundle) -> Unit) {
        parentFragmentManager.setFragmentResultListener(
            TARGET::class.qualifiedName ?: "",
            this,
            { _, result -> callback(result) }
        )
    }

    protected fun setFragmentResult(bundle: Bundle) {
        parentFragmentManager.setFragmentResult(viewModel.args::class.qualifiedName ?: "", bundle)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (activity?.isChangingConfigurations == false) {
            viewModel.clearState()
        }
        super.onSaveInstanceState(outState)
    }

    protected fun unhandledEffect(effect: Effect) {
        throw IllegalStateException(
            "Effect of type $effect is not handled by ${this.javaClass.name}." +
                " If you want to handle this intent then add support in when clause"
        )
    }

    protected inline fun <reified NAV : Navigator> ComponentCallbacks.navigator(): Lazy<NAV> {
        return inject { parametersOf(this) }
    }
}
