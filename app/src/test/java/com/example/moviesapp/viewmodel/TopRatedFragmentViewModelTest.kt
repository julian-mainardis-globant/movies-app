package com.example.moviesapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.entity.Movie
import com.example.domain.usecase.GetTopRatedMoviesUseCase
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
class TopRatedFragmentViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: TopRatedViewModel
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase = mock()
    private val listOfMovies: List<Movie> = mock()
    private val exception: Exception = mock()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        viewModel = TopRatedViewModel(getTopRatedMoviesUseCase)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getTopRatedMovies return success result`() {
        val liveData = viewModel.getMovieState().testObserver()

        whenever(getTopRatedMoviesUseCase()).thenReturn(Result.Success(listOfMovies))

        runBlocking {
            viewModel.getTopRatedMovies().join()
        }

        assertEquals(TopRatedViewModel.MovieState.RESPONSE_LOADING, liveData.observedValues[0]?.state)
        assertEquals(TopRatedViewModel.MovieState.RESPONSE_SUCCESS, liveData.observedValues[1]?.state)
    }

    @Test
    fun `when getTopRatedMovies return failure`() {
        val liveData = viewModel.getMovieState().testObserver()

        whenever(getTopRatedMoviesUseCase()).thenReturn(Result.Failure(exception = exception))

        runBlocking {
            viewModel.getTopRatedMovies().join()
        }

        assertEquals(TopRatedViewModel.MovieState.RESPONSE_LOADING, liveData.observedValues[0]?.state)
        assertEquals(TopRatedViewModel.MovieState.RESPONSE_ERROR, liveData.observedValues[1]?.state)
    }
}
