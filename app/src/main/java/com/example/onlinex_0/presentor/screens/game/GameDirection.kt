package com.example.onlinex_0.presentor.screens.game

import com.example.onlinex_0.presentor.screens.win.WinScreen
import com.example.onlinex_0.utils.navigator.AppNavigator
import javax.inject.Inject

interface GameDirection {

    suspend fun moveTopWin(name:String)
}

class GameDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : GameDirection {
    override suspend fun moveTopWin(name:String) {
        appNavigator.openWithSave(WinScreen(name))
    }

}