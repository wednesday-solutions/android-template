package com.wednesday.template.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.wednesday.template.presentation.base.extensions.asString
import com.wednesday.template.presentation.weather.home.HomeScreenIntent
import com.wednesday.template.presentation.weather.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        installSplashScreen()

        super.onCreate(savedInstanceState)

        viewModel.onCreate(null)
        setContent {
            val state by viewModel.screenState.observeAsState()

            Scaffold(modifier = Modifier.padding(16.dp)) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item(key = 1) {
                        Box {
                            TextObs(showLoading = state?.showLoading)
                        }
                    }
                    item(key = 2) { StateObserver() }
                    item(key = 3) {
                        Button(onClick = { viewModel.onIntent(HomeScreenIntent.Loading) }) {
                            Text("one")
                        }
                    }
                    item(key = 4) {
                        Button(onClick = { viewModel.onIntent(HomeScreenIntent.Loading2) }) {
                            Text("two")
                        }
                    }
                    item(key = 5) {
                        Button(onClick = { viewModel.onIntent(HomeScreenIntent.Loading3) }) {
                            Text("three")
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun TextObs(showLoading: Boolean?) {
        Timber.e(" ------ Text updating")
        Text("Hello from Compose $showLoading!")
    }

    @Composable
    fun StateObserver() {
        val state by viewModel.subState.collectAsState(initial = null)
        Timber.e(" ----- state observer updated")
        Column {
            T1(state = state?.title?.asString().toString())
            T2(state = state?.hasBackButton ?: false)
        }
    }

    @androidx.compose.runtime.Composable
    fun T1(state: String) {
        Timber.e(" ----- T1 updated $state")
        Text("State is $state")
    }

    @androidx.compose.runtime.Composable
    fun T2(state: Boolean) {
        Timber.e(" ----- T2 updated $state")
        Text("State is $state")
    }
}
