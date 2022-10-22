package com.example.moviesapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.entity.Movie
import com.example.domain.usecase.GetUpcomingMoviesUseCase
import com.example.domain.util.Result
import com.example.moviesapp.testObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UpcomingFragmentViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: UpcomingViewModel
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase = mock()
    private val listOfMovies: List<Movie> = mock()
    private val exception: Exception = mock()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UpcomingViewModel(getUpcomingMoviesUseCase)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getUpcomingMovies return success result`() {
        val liveData = viewModel.getMovieState().testObserver()

        whenever(getUpcomingMoviesUseCase()).thenReturn(Result.Success(listOfMovies))

        runBlocking {
            viewModel.getUpcomingMovies().join()
        }

        assertEquals(
            UpcomingViewModel.MovieState.RESPONSE_LOADING,
            liveData.observedValues[0]?.state
        )
        assertEquals(
            UpcomingViewModel.MovieState.RESPONSE_SUCCESS,
            liveData.observedValues[1]?.state
        )
    }

    @Test
    fun `when getUpcomingMovies return failure`() {
        val liveData = viewModel.getMovieState().testObserver()

        whenever(getUpcomingMoviesUseCase()).thenReturn(Result.Failure(exception = exception))

        runBlocking {
            viewModel.getUpcomingMovies().join()
        }

        assertEquals(
            UpcomingViewModel.MovieState.RESPONSE_LOADING,
            liveData.observedValues[0]?.state
        )
        assertEquals(
            UpcomingViewModel.MovieState.RESPONSE_ERROR,
            liveData.observedValues[1]?.state
        )
    }
}
