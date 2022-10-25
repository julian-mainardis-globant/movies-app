package com.example.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private var mainActivityState = MutableLiveData<MainActivityData>()
    fun getMainActivityState(): LiveData<MainActivityData> = mainActivityState

    fun initialize() {
        mainActivityState.value = MainActivityData(MainActivityState.INITIALIZED)
    }

    fun searchPressed() {
        mainActivityState.value = MainActivityData(MainActivityState.SEARCH)
    }

    data class MainActivityData(
        val state: MainActivityState
    )

    enum class MainActivityState {
        INITIALIZED,
        SEARCH
    }
}
