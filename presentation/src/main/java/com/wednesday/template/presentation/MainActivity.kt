package com.wednesday.template.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wednesday.template.presentation.base.viewmodel.BaseViewModel
import com.wednesday.template.presentation.routes.Routes
import com.wednesday.template.presentation.weather.home.HomeScreenIntent
import com.wednesday.template.presentation.weather.home.HomeScreenState
import com.wednesday.template.presentation.weather.home.HomeViewModel
import com.wednesday.template.presentation.weather.home.LocalHomeViewModel
import com.wednesday.template.resources.databinding.ActivityMainBinding
import org.koin.androidx.compose.get
import org.koin.androidx.compose.viewModel
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Navigation()
            }
        }
    }

    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        GlobalContext.loadKoinModules(module {
            single { navController }
        })
        NavHost(navController = navController, startDestination = Routes.HOME) {
            composable(route = Routes.HOME) {
                HomePage()
            }
            composable(route = Routes.SEARCH) {
                SearchPage()
            }
        }
    }

    @Composable
    fun HomePage() {
        val viewModel by viewModel<HomeViewModel>()
        CompositionLocalProvider(LocalHomeViewModel provides viewModel) {
            HomePageBody()
        }
    }

    @Composable
    fun HomePageBody() {
        val viewModel = LocalHomeViewModel.current
        val state = viewModel.screenState.observeAsState(initial = viewModel.getDefaultScreenState())
        viewModel.onCreate(false)
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = state.value.toString(), color = Color.White, maxLines = 5, overflow = TextOverflow.Ellipsis)
            Button(onClick = {
                viewModel.onIntent(HomeScreenIntent.Search)
            }) {
                Text(text = "To Search")
            }
        }
    }

    @Composable
    fun SearchPage() {
        Text(text = "Search", color = Color.White)
    }

//    @SuppressLint("ResourceType")
//    private fun displayFragment() {
//        val (graph, controller) = getNavGraphWithController(
//            binding.mainNavHostFragment.id,
//            R.navigation.nav_main
//        )
//
//        val startScreen = HomeScreen
//
//        graph.setup(
//            controller,
//            R.id.startFragment,
//            bundleOf("key_args" to startScreen)
//        )
//    }
//
//    private fun NavGraph.setup(
//        navController: NavController,
//        startDestId: Int,
//        startDestinationArgs: Bundle? = null
//    ) {
//        startDestination = startDestId
//        if (startDestinationArgs != null) {
//            navController.setGraph(this, startDestinationArgs)
//        } else {
//            navController.graph = this
//        }
//    }

//    private fun FragmentActivity.getNavGraphWithController(
//        @IdRes navHostFragmentId: Int,
//        @NavigationRes navGraphId: Int
//    ): NavGraphWithController {
//        val navHost =
//            supportFragmentManager.findFragmentById(navHostFragmentId) as NavHostFragment
//        val navController = navHost.findNavController()
//        val navInflater = navController.navInflater
//        val graph = navInflater.inflate(navGraphId)
//
//        return NavGraphWithController(graph, navController)
//    }
//
//    private data class NavGraphWithController(
//        val graph: NavGraph,
//        val controller: NavController
//    )
}
