package com.example.data.mapper

import com.example.data.service.util.transformToMovieEntity
import com.example.domain.entity.Movie
import com.example.domain.util.TabsEnum
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieMapperTest {

    private lateinit var movie: Movie

    @Before
    fun init() {
        movie = Movie(id = ID, title = TITLE, imgURL = IMG_URL, voteAverage = VOTE_AVERAGE.toFloat(), tab = TabsEnum.NOW_PLAYING)
    }

    @Test
    fun `transform local movie to entity movie`() {
        val localCharacterTransformed = movie.transformToMovieEntity()
        assertEquals(localCharacterTransformed.id, movie.id)
        assertEquals(localCharacterTransformed.title, movie.title)
        assertEquals(localCharacterTransformed.imgURL, movie.imgURL)
        assertEquals(localCharacterTransformed.voteAverage, movie.voteAverage)
        assertEquals(localCharacterTransformed.tab, movie.tab)
    }

    companion object {
        const val ID = "1"
        const val TITLE = "The lord of the rings"
        const val IMG_URL = "https://image.tmdb.org/t/p/w500/pHkKbIRoCe7zIFvqan9LFSaQAde.jpg"
        const val VOTE_AVERAGE = 9.9
    }
}
