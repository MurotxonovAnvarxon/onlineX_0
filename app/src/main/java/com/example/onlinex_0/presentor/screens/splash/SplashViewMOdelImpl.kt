package com.example.onlinex_0.presentor.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val direction: SplashDirection
) : ViewModel(), SplashContract.ViewModel {


    init {
        viewModelScope.launch {
            delay(2000L)
            direction.openStart()
        }
    }
}