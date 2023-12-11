package com.example.onlinex_0.presentor.screens.users

import com.example.onlinex_0.presentor.screens.game.GameScreen
import com.example.onlinex_0.utils.navigator.AppNavigator
import javax.inject.Inject

interface UsersDirection {
    suspend fun moveToGameScreen(name: String)
}

class UserDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : UsersDirection {
    override suspend fun moveToGameScreen(name: String) {
        appNavigator.openWithSave(GameScreen(name))
    }

}