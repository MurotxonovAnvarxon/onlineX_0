package com.example.onlinex_0.presentor.screens.users

import com.example.onlinex_0.data.common.UserUiCommon
import kotlinx.coroutines.flow.StateFlow

interface UsersScreenContract {

    interface ViewModel {

        val uiState: StateFlow<UIState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val list: List<UserUiCommon> = listOf()
    )


    interface Intent {
        data class EnterUser(
            val id: String = "",
            val name: String = ""
        ) : Intent

        object LoadData : Intent
    }


}