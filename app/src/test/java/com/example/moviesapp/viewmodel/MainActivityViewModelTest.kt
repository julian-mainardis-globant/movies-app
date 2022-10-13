package com.example.moviesapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapp.testObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        mainActivityViewModel = MainActivityViewModel()
    }

    @Test
    fun `show loading state when there is connection`() {
        val moviesLiveData = mainActivityViewModel.getMovieState().testObserver()
        mainActivityViewModel.getMovies(true)
        assertEquals(
            MainActivityViewModel.MoviesState.LOADING,
            moviesLiveData.observedValues[0]?.mState
        )
    }

    @Test
    fun `show empty state when there is no connection and local database is empty`() {
        val moviesLiveData = mainActivityViewModel.getMovieState().testObserver()
        mainActivityViewModel.getMovies(false)
        assertEquals(
            MainActivityViewModel.MoviesState.LOADING,
            moviesLiveData.observedValues[0]?.mState
        )
        assertEquals(
            MainActivityViewModel.MoviesState.EMPTY_DATABASE,
            moviesLiveData.observedValues[1]?.mState
        )
    }
}
