package com.example.moviesapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashScreenViewModelTest {
    private var splashViewModel = SplashScreenViewModel()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `set livedata with START as value`() {
        splashViewModel.startAnimation()

        Assert.assertEquals(
            SplashScreenViewModel.SplashScreenState.START,
            splashViewModel.getSplashState().value?.state
        )
    }

    @Test
    fun `set livedata with DONE as value`() {
        splashViewModel.splashDone()

        Assert.assertEquals(
            SplashScreenViewModel.SplashScreenState.DONE,
            splashViewModel.getSplashState().value?.state
        )
    }
}
