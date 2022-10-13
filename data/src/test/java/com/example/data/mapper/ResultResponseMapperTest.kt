package com.example.data.mapper

import com.example.data.service.response.MovieResponse
import com.example.data.service.response.ResultResponse
import com.example.data.service.util.getImgURL
import com.example.data.service.util.transformToLocalMovieList
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ResultResponseMapperTest {

    private lateinit var resultResponse: ResultResponse

    @Before
    fun init() {
        resultResponse = ResultResponse(mutableListOf(MovieResponse(id = ID, title = TITLE, imgPath = IMG_PATH)))
    }

    @Test
    fun `transform data response to local movie list`() {
        val response = resultResponse.transformToLocalMovieList()
        assertEquals(response[0].id, resultResponse.results[0].id)
        assertEquals(response[0].title, resultResponse.results[0].title)
        assertEquals(response[0].imgURL, resultResponse.results[0].getImgURL())
    }

    companion object {
        const val ID = "1"
        const val TITLE = "The lord of the rings"
        const val BASE_URL_IMG = "https://image.tmdb.org/t/p/w500"
        const val IMG_PATH = "/pHkKbIRoCe7zIFvqan9LFSaQAde.jpg"
    }
}
