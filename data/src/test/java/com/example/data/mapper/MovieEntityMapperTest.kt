package com.example.data.mapper

import com.example.data.entity.MovieEntity
import com.example.data.service.util.transformToLocalMovie
import com.example.data.service.util.transformToLocalMoviesList
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieEntityMapperTest {

    private lateinit var movieEntity: MovieEntity
    private lateinit var movieEntityList: List<MovieEntity>

    @Before
    fun init() {
        movieEntity = MovieEntity(id = ID, title = TITLE, imgURL = IMG_URL)
        movieEntityList = mutableListOf(MovieEntity(id = ID, title = TITLE, imgURL = IMG_URL))
    }

    @Test
    fun `transform entity movie to local movie`() {
        val characterEntityTransformed = movieEntity.transformToLocalMovie()
        assertEquals(characterEntityTransformed.id, movieEntity.id)
        assertEquals(characterEntityTransformed.title, movieEntity.title)
    }

    @Test
    fun `transform list of entity movie to list of local movie`() {
        val characterEntityListTransformed = movieEntityList.transformToLocalMoviesList()
        assertEquals(characterEntityListTransformed[0].id, movieEntityList[0].id)
        assertEquals(characterEntityListTransformed[0].title, movieEntityList[0].title)
    }

    companion object {
        const val ID = "1"
        const val TITLE = "The lord of the rings"
        const val IMG_URL = "https://image.tmdb.org/t/p/w500/pHkKbIRoCe7zIFvqan9LFSaQAde.jpg"
    }
}
