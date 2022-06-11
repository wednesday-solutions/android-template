package com.wednesday.template.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.core.os.bundleOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.wednesday.template.presentation.base.scaffold.AppScaffold
import com.wednesday.template.presentation.base.theme.AppTheme
import com.wednesday.template.presentation.weather.home.HomeScreen
import com.wednesday.template.presentation.weather.home.HomeScreenIntent
import com.wednesday.template.presentation.weather.home.HomeViewModel
import com.wednesday.template.resources.databinding.ActivityMainBinding
import org.koin.androidx.compose.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @OptIn(ExperimentalMaterial3Api::class)
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
                AppScaffold(
                    effectState = viewModel.effectState
                ) {
                    Box {
                        Column {
                            Text(text = "Welcome to Compose!")
                            Button(onClick = {
                                viewModel.onIntent(HomeScreenIntent.Loading)
                            }) {
                                Text(text = "Show Dialog")
                            }
                            Button(onClick = {
                                viewModel.onIntent(HomeScreenIntent.Search)
                            }) {
                                Text(text = "Show Snackbar")
                            }
                        }
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
