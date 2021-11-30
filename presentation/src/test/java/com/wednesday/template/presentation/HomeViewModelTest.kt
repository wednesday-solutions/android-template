package com.wednesday.template.presentation

import com.wednesday.template.interactor.weather.FavouriteWeatherInteractor
import com.wednesday.template.navigation.home.HomeNavigator
import com.wednesday.template.presentation.base.BaseViewModelTest
import com.wednesday.template.presentation.base.UIList
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.weather.home.HomeScreenState
import com.wednesday.template.presentation.weather.home.HomeViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyZeroInteractions
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class HomeViewModelTest : BaseViewModelTest() {

    private lateinit var interactor: FavouriteWeatherInteractor
    private lateinit var navigator: HomeNavigator
    private lateinit var viewModel: HomeViewModel

    override fun before() {
        interactor = mock()
        navigator = mock()
        viewModel = HomeViewModel(
            favouriteWeatherInteractor = interactor
        )
    }

    override fun after() = Unit

    @Test
    fun `Given _, When getDefaultScreenState, Then it returns correct state`() {
        // Given

        // When
        val screenState = viewModel.getDefaultScreenState()

        // Then
        val expected = getInitialState()
        assertEquals(expected, screenState)
    }

    @Test
    fun `Given fromRecreate = true, When onCreate, Then interactor was not called`() =
        runBlocking {
            // Given
            val fromRecreate = true

            // When
            viewModel.onCreate(fromRecreate = fromRecreate)

            // Then
            verifyZeroInteractions(interactor)
        }

    @Test
    fun `Given fromRecreate = false, When onCreate, Then FavouriteCitiesFlow and FavouriteWeatherUIList were called`() =
        runBlocking {
            // Given
            val fromRecreate = false
            whenever(interactor.getFavouriteCitiesFlow())
                .thenReturn(flow { })
            whenever(interactor.getFavouriteWeatherUIList())
                .thenReturn(flow { })

            // When
            viewModel.onCreate(fromRecreate = fromRecreate)

            // Then
            verify(interactor, times(1)).getFavouriteWeatherUIList()
            verify(interactor, times(1)).getFavouriteCitiesFlow()
            Unit
        }

    private fun getInitialState() = HomeScreenState(
        toolbar = UIToolbar(
            title = UIText { block("Weather") },
            hasBackButton = false,
            menuIcon = R.drawable.ic_search,
        ),
        showLoading = false,
        items = UIList()
    )
}
