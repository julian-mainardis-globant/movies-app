package com.example.moviesapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.entity.Movie
import com.example.domain.usecase.GetNowPlayingMoviesUseCase
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
class NowPlayingFragmentViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: NowPlayingViewModel
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase = mock()
    private val listOfMovies: List<Movie> = mock()
    private val exception: Exception = mock()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        viewModel = NowPlayingViewModel(getNowPlayingMoviesUseCase)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getNowPlayingMovies return success result`() {
        val liveData = viewModel.getMovieState().testObserver()

        whenever(getNowPlayingMoviesUseCase()).thenReturn(Result.Success(listOfMovies))

        runBlocking {
            viewModel.getNowPlayingMovies().join()
        }

        assertEquals(NowPlayingViewModel.MovieState.RESPONSE_LOADING, liveData.observedValues[0]?.state)
        assertEquals(NowPlayingViewModel.MovieState.RESPONSE_SUCCESS, liveData.observedValues[1]?.state)
    }

    @Test
    fun `when getNowPlayingMovies return failure`() {
        val liveData = viewModel.getMovieState().testObserver()

        whenever(getNowPlayingMoviesUseCase()).thenReturn(Result.Failure(exception = exception))

        runBlocking {
            viewModel.getNowPlayingMovies().join()
        }

        assertEquals(NowPlayingViewModel.MovieState.RESPONSE_LOADING, liveData.observedValues[0]?.state)
        assertEquals(NowPlayingViewModel.MovieState.RESPONSE_ERROR, liveData.observedValues[1]?.state)
    }
}
