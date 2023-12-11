package com.example.onlinex_0.presentor.screens.game

import com.example.onlinex_0.data.common.GameUiCommon
import kotlinx.coroutines.flow.StateFlow

interface GameContract {

    interface ViewModel {

        fun onEventDispatcher(intent: Intent)
        val uiState: StateFlow<UIState>

    }


    data class UIState(
        val gameUICommon: GameUiCommon? = null,
        val check: Boolean = true,
        val checkWin: Boolean = false,
        val winName: String = ""
    )


    interface Intent {
        object LoadData : Intent
        object AfterLoadingData : Intent

        data class UpdateMap(
            val dataUICommon: GameUiCommon,
            val newMap: String
        ) : Intent

        data class MoveToWinScreen(
            val name: String
        ) : Intent

        data class Winner(
            val name: String
        ) : Intent
    }

}