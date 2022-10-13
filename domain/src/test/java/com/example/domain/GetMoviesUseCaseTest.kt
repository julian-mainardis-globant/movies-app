package com.example.domain

import com.example.domain.database.MovieRepository
import com.example.domain.entity.Movie
import com.example.domain.service.MovieService
import com.example.domain.usecase.GetNowPlayingMoviesUseCase
import com.example.domain.usecase.GetNowPlayingMoviesUseCaseImpl
import com.example.domain.util.Result
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetMoviesUseCaseTest {

    private lateinit var getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
    private val movieService: MovieService = mock()
    private val database: MovieRepository = mock()
    private val movieList: List<Movie> = mock()

    @Before
    fun init() {
        getNowPlayingMoviesUseCase = GetNowPlayingMoviesUseCaseImpl(movieService, database)
    }

    @Test
    fun `when the service return a success result`() {
        whenever(movieService.getNowPlayingMovies()).thenReturn(Result.Success(movieList))
        whenever(database.getNowPlayingMovies()).thenReturn(Result.Success(movieList))

        val result = getNowPlayingMoviesUseCase()

        verify(database).updateNowPlayingMovies(movieList)
        verify(database).getNowPlayingMovies()

        Assert.assertEquals(movieList, (result as Result.Success).data)
    }

    @Test
    fun `when the service return a failure result and the database is empty`() {
        whenever(movieService.getNowPlayingMovies()).thenReturn(Result.Failure(Exception(NOT_FOUND)))
        whenever(database.getNowPlayingMovies()).thenReturn(Result.Failure(Exception(NOT_FOUND)))

        val result = getNowPlayingMoviesUseCase()

        verify(database).getNowPlayingMovies()

        Assert.assertEquals(NOT_FOUND, (result as Result.Failure).exception.message)
    }

    @Test
    fun `when the service return a failure result and the database isn't empty`() {
        whenever(movieService.getNowPlayingMovies()).thenReturn(Result.Failure(Exception(NOT_FOUND)))
        whenever(database.getNowPlayingMovies()).thenReturn(Result.Success(movieList))

        val result = getNowPlayingMoviesUseCase()

        verify(database).getNowPlayingMovies()

        Assert.assertEquals(movieList, (result as Result.Success).data)
    }

    companion object {
        private const val NOT_FOUND = "NOT_FOUND"
    }
}
