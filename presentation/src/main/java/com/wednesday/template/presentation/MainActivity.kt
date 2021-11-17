package com.wednesday.template.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.wednesday.template.presentation.weather.search.SearchFragmentScreen
import com.wednesday.template.resources.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Checking
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        displayFragment()
        setContentView(binding.root)
    }

    @SuppressLint("ResourceType")
    private fun displayFragment() {
        val (graph, controller) = getNavGraphWithController(
            binding.mainNavHostFragment.id,
            R.navigation.nav_main
        )

        val searchFragmentScreen = SearchFragmentScreen

        graph.setup(
            controller,
            R.id.searchFragment,
            bundleOf("key_args" to searchFragmentScreen)
        )
    }

    private fun NavGraph.setup(
        navController: NavController,
        startDestId: Int,
        startDestinationArgs: Bundle? = null
    ) {
        startDestination = startDestId
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
