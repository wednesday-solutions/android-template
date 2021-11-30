package com.wednesday.template.presentation.test.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.wednesday.template.interactor.weather.FavouriteWeatherInteractor
import com.wednesday.template.interactor.weather.SearchCityInteractor
import com.wednesday.template.navigation.search.SearchNavigator
import com.wednesday.template.presentation.weather.search.SearchFragmentViewModel
import com.wednesday.template.presentation.weather.search.SearchScreenIntent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

abstract class BaseViewModelTest {

//    @get:Rule
//    val coroutinesDispatcherRule = CoroutineDispatcherRule()
    
    @ExperimentalCoroutinesApi
    val testDispatcher = TestCoroutineDispatcher()
    
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    
    abstract fun before()
    
    abstract fun after()
    
    @Before
    fun internalBefore() {
        val testModule = module {
            factory { SavedStateHandle() }
        }
        startKoin {
            modules(testModule)
        }
        before()
    }
    
    @After
    fun internalAfter() {
        after()
        stopKoin()
    }
    
    protected fun <T> mockObserver() = mock<Observer<T?>>()
}

class SearchFragmentViewModelTest:BaseViewModelTest(){
    
    private lateinit var searchCityInteractor: SearchCityInteractor
    private lateinit var favouriteWeatherInteractor: FavouriteWeatherInteractor
    private lateinit var navigator: SearchNavigator
    private lateinit var searchFragmentViewModel :SearchFragmentViewModel
    
    @ExperimentalCoroutinesApi
    override fun before() {
        Dispatchers.setMain(testDispatcher)
    
        searchCityInteractor = mock()
        favouriteWeatherInteractor = mock()
        navigator = mock()
    
        searchFragmentViewModel =
            SearchFragmentViewModel(
                searchCityInteractor,
                favouriteWeatherInteractor,
                navigator
            )
    }
    
    @ExperimentalCoroutinesApi
    override fun after() {
    
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
    
    @ExperimentalCoroutinesApi
    @Test
    fun `Given the city name, When searchCityInteractor is called, Then verify one call to search `() = runBlockingTest{
        //Given
        val city = "Pune"
        whenever(searchCityInteractor.search(city)).thenReturn(Unit)
        //When
        searchFragmentViewModel.onIntent(SearchScreenIntent.SearchCities(city))
        //Then
        verify(searchCityInteractor, times(1)).search(city)
    }
}