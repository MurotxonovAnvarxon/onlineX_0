package com.example.onlinex_0.presentor.screens.splash

import com.example.onlinex_0.presentor.screens.start.StartScreen
import com.example.onlinex_0.utils.navigator.AppNavigator
import javax.inject.Inject

interface SplashDirection {
    suspend fun openStart()
}

class SplashDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : SplashDirection {
    override suspend fun openStart() {
        appNavigator.openWithSave(StartScreen())
    }
}