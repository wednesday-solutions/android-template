package com.wednesday.template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        installSplashScreen()

        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        displayFragment()
//        setContentView(binding.root)
    }

//    @SuppressLint("ResourceType")
//    private fun displayFragment() {
//        val (graph, controller) = getNavGraphWithController(
//            binding.mainNavHostFragment.id,
//            R.navigation.nav_main
//        )
//
//        val startScreen = com.wednesday.template.home.presentation.HomeScreen
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
//        setStartDestination(startDestId)
//        if (startDestinationArgs != null) {
//            navController.setGraph(this, startDestinationArgs)
//        } else {
//            navController.graph = this
//        }
//    }
//
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
