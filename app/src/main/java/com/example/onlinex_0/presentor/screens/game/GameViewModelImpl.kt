package com.example.onlinex_0.presentor.screens.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinex_0.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameViewModelImpl @Inject constructor(
    private val appRepository: AppRepository,
    private val direction: GameDirection
) : ViewModel(), GameContract.ViewModel {


    override val uiState = MutableStateFlow<GameContract.UIState>(GameContract.UIState())


    private fun loadData() {

        appRepository.gameDataStateFlow
            .onEach {
                it?.let { data ->
                    if (it.winner) {
                        appRepository.removeAllData()
                        appRepository.removeValue()
                        direction.moveTopWin(it.winnerName)
                    }
                    reduce { it.copy(gameUICommon = data) }
                }
            }
            .launchIn(viewModelScope)

    }

    var name = ""


    private fun reduce(block: (GameContract.UIState) -> GameContract.UIState) {
        val oldValue = uiState.value
        uiState.value = block(oldValue)
    }

    override fun onEventDispatcher(intent: GameContract.Intent) {
        when (intent) {

            GameContract.Intent.AfterLoadingData -> {
                reduce { it.copy(check = false) }
            }

            GameContract.Intent.LoadData -> {
                loadData()
            }

            is GameContract.Intent.UpdateMap -> {
                appRepository.updateData(intent.dataUICommon, intent.newMap)
                    .onEach {
                        it.onSuccess {
                        }
                    }
                    .launchIn(viewModelScope)
            }

            is GameContract.Intent.MoveToWinScreen -> {
                viewModelScope.launch {
                    name = intent.name
                    appRepository.removeAllData()
                    appRepository.removeValue()
                    direction.moveTopWin(name)
                }
            }

            is GameContract.Intent.Winner -> {
                reduce { it.copy(winName = intent.name) }
                reduce { it.copy(checkWin = true) }
            }

        }
    }
}


