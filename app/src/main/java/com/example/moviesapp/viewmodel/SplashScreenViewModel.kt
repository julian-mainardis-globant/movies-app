package com.example.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashScreenViewModel : ViewModel() {

    private var mutableLiveData = MutableLiveData<SplashScreenData>()
    fun getSplashState(): LiveData<SplashScreenData> = mutableLiveData

    fun startAnimation() {
        mutableLiveData.value = SplashScreenData(SplashScreenState.START)
    }

    fun splashDone() {
        mutableLiveData.value = SplashScreenData(SplashScreenState.DONE)
    }

    data class SplashScreenData(
        val state: SplashScreenState
    )

    enum class SplashScreenState {
        START,
        DONE
    }
}
