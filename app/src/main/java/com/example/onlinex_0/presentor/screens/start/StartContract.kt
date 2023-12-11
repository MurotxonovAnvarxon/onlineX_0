package com.example.onlinex_0.presentor.screens.start

import kotlinx.coroutines.flow.StateFlow
import java.lang.Thread.State

interface StartContract {


    interface ViewModel {
        val uiState: StateFlow<UIState>
        fun onEventDispatcher(intent: Intent)
    }


    data class UIState(
        val name: String = ""
    )


    interface Intent {
        data class SaveName(val name: String) : Intent
    }


}