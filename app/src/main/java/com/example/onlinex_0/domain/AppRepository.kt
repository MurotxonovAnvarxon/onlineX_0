package com.example.onlinex_0.domain

import com.example.onlinex_0.data.common.GameUiCommon
import com.example.onlinex_0.data.common.UserUiCommon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AppRepository {


    val userDataStateFlow: StateFlow<List<UserUiCommon>>
    val gameDataStateFlow: StateFlow<GameUiCommon?>
    val battleFlow: StateFlow<String?>

    fun addUser(user: String): Flow<Result<Unit>>
    fun getAllUsers(): Flow<Result<List<UserUiCommon>>>
    fun attachUser(pairId: String, pairName: String): Flow<Result<Unit>>
    fun updateData(dataUICommon: GameUiCommon, newMap: String): Flow<Result<Unit>>
    fun removeValue()
    fun getName(): String
    fun removeAllData()
}