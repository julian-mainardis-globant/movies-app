package com.example.moviesapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.database.MovieRepository
import com.example.domain.entity.Movie
import com.example.domain.util.Result
import com.example.domain.util.TabsEnum
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
class SearchViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: SearchActivityViewModel
    private val movieDB: MovieRepository = mock()
    private val listOfMovies: List<Movie> = mock()
    private val exception: Exception = mock()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SearchActivityViewModel(movieDB)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getMovies return success result`() {
        val liveData = viewModel.getSearchState().testObserver()

        whenever(movieDB.getMovies()).thenReturn(Result.Success(listOfMovies))

        runBlocking {
            viewModel.getMovies().join()
        }

        assertEquals(
            SearchActivityViewModel.SearchState.LOADING,
            liveData.observedValues[0]?.state
        )
        assertEquals(
            SearchActivityViewModel.SearchState.SUCCESS,
            liveData.observedValues[1]?.state
        )
    }

    @Test
    fun `when getMovies return failure`() {
        val liveData = viewModel.getSearchState().testObserver()

        whenever(movieDB.getMovies()).thenReturn(Result.Failure(exception = exception))

        runBlocking {
            viewModel.getMovies().join()
        }

        assertEquals(
            SearchActivityViewModel.SearchState.LOADING,
            liveData.observedValues[0]?.state
        )
        assertEquals(
            SearchActivityViewModel.SearchState.ERROR,
            liveData.observedValues[1]?.state
        )
    }

    @Test
    fun `movie filter`() {
        val liveData = viewModel.getSearchState().testObserver()
        val moviesList = mutableListOf<Movie>()
        moviesList.add(Movie(EMPTY, TITLE1, EMPTY, FLOAT, TabsEnum.TOP_RATED))
        moviesList.add(Movie(EMPTY, TITLE2, EMPTY, FLOAT, TabsEnum.TOP_RATED))

        viewModel.filter(FILTER, moviesList)

        assertEquals(
            SearchActivityViewModel.SearchState.FILTERED,
            liveData.observedValues[0]?.state
        )

        assertEquals(
            TITLE1,
            liveData.observedValues[0]?.data?.get(0)?.title
        )

        assertEquals(
            SIZE,
            liveData.observedValues[0]?.data?.size
        )
    }

    companion object {
        const val FILTER = "JOkeR"
        const val TITLE1 = "Joker"
        const val TITLE2 = "Batman"
        const val EMPTY = ""
        const val FLOAT = 5.5f
        const val SIZE = 1
    }
}
