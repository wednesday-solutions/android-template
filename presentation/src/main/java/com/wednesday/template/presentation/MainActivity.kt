package com.wednesday.template.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.os.bundleOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.wednesday.template.presentation.base.compositionLocals.LocalDialogHostState
import com.wednesday.template.presentation.base.compositionLocals.LocalSnackbarHostState
import com.wednesday.template.presentation.base.effect.EffectHandler
import com.wednesday.template.presentation.base.effect.ShowAlertDialogEffect
import com.wednesday.template.presentation.base.effect.ShowSnackbarEffect
import com.wednesday.template.presentation.base.effect.unhandledEffect
import com.wednesday.template.presentation.base.extensions.asUIText
import com.wednesday.template.presentation.base.extensions.showSnackbar
import com.wednesday.template.presentation.base.list.UILazyColumn
import com.wednesday.template.presentation.base.scaffold.AppScaffold
import com.wednesday.template.presentation.base.theme.AppTheme
import com.wednesday.template.presentation.weather.UICity
import com.wednesday.template.presentation.weather.home.HomeScreen
import com.wednesday.template.presentation.weather.home.HomeViewModel
import com.wednesday.template.presentation.weather.search.list.UICityListRenderer
import com.wednesday.template.resources.databinding.ActivityMainBinding
import org.koin.androidx.compose.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        displayFragment()
//        setContentView(binding.root)

        setContent {
            val viewModel by viewModel<HomeViewModel>()

            AppTheme {
                AppScaffold {
                    val snackbarHostState = LocalSnackbarHostState.current
                    val dialogHostState = LocalDialogHostState.current
                    EffectHandler(effectFlow = viewModel.effectState) {
                        when (it) {
                            is ShowSnackbarEffect -> snackbarHostState.showSnackbar(it)
                            is ShowAlertDialogEffect -> dialogHostState.showDialog(it)
                            else -> unhandledEffect()
                        }
                    }

                    Box {
                        UILazyColumn(
                            renderers = mapOf(
                                UICity::class to UICityListRenderer()
                            ),
                            items = listOf(
                                UICity(
                                    cityId = 1,
                                    title = "Moscow",
                                    state = "Moscow",
                                    displayTitle = "Moscow".asUIText(),
                                    locationType = "Moscow",
                                    displayLocationType = "Moscow".asUIText(),
                                    latitude = "55",
                                    isFavourite = true
                                ),
                                UICity(
                                    cityId = 2,
                                    title = "Moscow",
                                    state = "Moscow",
                                    displayTitle = "Moscow".asUIText(),
                                    locationType = "Moscow",
                                    displayLocationType = "Moscow".asUIText(),
                                    latitude = "55",
                                    isFavourite = true
                                ),
                                UICity(
                                    cityId = 3,
                                    title = "Moscow",
                                    state = "Moscow",
                                    displayTitle = "Moscow".asUIText(),
                                    locationType = "Moscow",
                                    displayLocationType = "Moscow".asUIText(),
                                    latitude = "55",
                                    isFavourite = true
                                ),
                            ),
                            onIntent = viewModel::onIntent
                        )
                    }
                }
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun displayFragment() {
        val (graph, controller) = getNavGraphWithController(
            binding.mainNavHostFragment.id,
            R.navigation.nav_main
        )

        val startScreen = HomeScreen

        graph.setup(
            controller,
            R.id.startFragment,
            bundleOf("key_args" to startScreen)
        )
    }

    private fun NavGraph.setup(
        navController: NavController,
        startDestId: Int,
        startDestinationArgs: Bundle? = null
    ) {
        setStartDestination(startDestId)
        if (startDestinationArgs != null) {
            navController.setGraph(this, startDestinationArgs)
        } else {
            navController.graph = this
        }
    }

    private fun FragmentActivity.getNavGraphWithController(
        @IdRes navHostFragmentId: Int,
        @NavigationRes navGraphId: Int
    ): NavGraphWithController {
        val navHost =
            supportFragmentManager.findFragmentById(navHostFragmentId) as NavHostFragment
        val navController = navHost.findNavController()
        val navInflater = navController.navInflater
        val graph = navInflater.inflate(navGraphId)

        return NavGraphWithController(graph, navController)
    }

    private data class NavGraphWithController(
        val graph: NavGraph,
        val controller: NavController
    )
}

data class DialogData(
    val title: String
)
