package com.example.data.mapper

import com.example.data.service.response.MovieResponse
import com.example.data.service.response.ResultResponse
import com.example.data.service.util.getImgURL
import com.example.data.service.util.transformToLocalMovieList
import com.example.domain.util.TabsEnum
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
        resultResponse =
            ResultResponse(mutableListOf(MovieResponse(id = ID, title = TITLE, voteAverage = VOTE_AVERAGE.toFloat(), imgPath = IMG_PATH)))
    }

    @Test
    fun `transform data response to local movie list`() {
        val response = resultResponse.transformToLocalMovieList(TabsEnum.NOW_PLAYING)
        assertEquals(response[0].id, resultResponse.results[0].id)
        assertEquals(response[0].title, resultResponse.results[0].title)
        assertEquals(response[0].imgURL, resultResponse.results[0].getImgURL())
        assertEquals(response[0].voteAverage, resultResponse.results[0].voteAverage)
    }

    companion object {
        const val ID = "1"
        const val TITLE = "The lord of the rings"
        const val IMG_PATH = "/pHkKbIRoCe7zIFvqan9LFSaQAde.jpg"
        const val VOTE_AVERAGE = 9.9
    }
}
