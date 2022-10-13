package com.example.moviesapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        mainActivityViewModel = MainActivityViewModel()
    }

    @Test
    fun `check the initialization of the app`() {
        mainActivityViewModel.initialize()

        Assert.assertEquals(
            MainActivityViewModel.MainActivityState.INITIALIZED,
            mainActivityViewModel.getMainActivityState().value?.state
        )
    }
}
