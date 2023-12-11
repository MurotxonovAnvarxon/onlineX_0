package com.example.onlinex_0.presentor.screens.start

import com.example.onlinex_0.presentor.screens.game.GameScreen
import com.example.onlinex_0.presentor.screens.users.UsersScreen
import com.example.onlinex_0.utils.navigator.AppNavigator
import javax.inject.Inject

interface StartDirection {
    suspend fun moveToUsers()
}


class StartDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator

) : StartDirection {

    override suspend fun moveToUsers() {
        appNavigator.openWithoutSave(UsersScreen())
    }


}