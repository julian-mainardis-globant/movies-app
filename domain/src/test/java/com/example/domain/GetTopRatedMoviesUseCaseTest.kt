package com.example.domain

import com.example.domain.database.MovieRepository
import com.example.domain.entity.Movie
import com.example.domain.service.MovieService
import com.example.domain.usecase.GetTopRatedMoviesUseCase
import com.example.domain.usecase.GetTopRatedMoviesUseCaseImpl
import com.example.domain.util.Result
import com.example.domain.util.TabsEnum
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetTopRatedMoviesUseCaseTest {

    private lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
    private val movieService: MovieService = mock()
    private val database: MovieRepository = mock()
    private val movieList: List<Movie> = mock()

    @Before
    fun init() {
        getTopRatedMoviesUseCase = GetTopRatedMoviesUseCaseImpl(movieService, database)
    }

    @Test
    fun `when the service return a success result`() {
        whenever(movieService.getTopRatedMovies()).thenReturn(Result.Success(movieList))
        whenever(database.getMoviesByTab(TAB)).thenReturn(Result.Success(movieList))

        val result = getTopRatedMoviesUseCase()

        verify(database).updateMovies(movieList)
        verify(database).getMoviesByTab(TAB)

        Assert.assertEquals(movieList, (result as Result.Success).data)
    }

    @Test
    fun `when the service return a failure result and the database is empty`() {
        whenever(movieService.getTopRatedMovies()).thenReturn(Result.Failure(Exception(NOT_FOUND)))
        whenever(database.getMoviesByTab(TAB)).thenReturn(Result.Failure(Exception(NOT_FOUND)))

        val result = getTopRatedMoviesUseCase()

        verify(database).getMoviesByTab(TAB)

        Assert.assertEquals(NOT_FOUND, (result as Result.Failure).exception.message)
    }

    @Test
    fun `when the service return a failure result and the database isn't empty`() {
        whenever(movieService.getTopRatedMovies()).thenReturn(Result.Failure(Exception(NOT_FOUND)))
        whenever(database.getMoviesByTab(TAB)).thenReturn(Result.Success(movieList))

        val result = getTopRatedMoviesUseCase()

        verify(database).getMoviesByTab(TAB)

        Assert.assertEquals(movieList, (result as Result.Success).data)
    }

    companion object {
        private const val NOT_FOUND = "NOT_FOUND"
        private val TAB = TabsEnum.TOP_RATED
    }
}
