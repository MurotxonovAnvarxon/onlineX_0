package com.example.onlinex_0.presentor.screens.users

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinex_0.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class UserScreenViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val direction: UsersDirection
) : ViewModel(), UsersScreenContract.ViewModel {
    override val uiState =
        MutableStateFlow<UsersScreenContract.UIState>(UsersScreenContract.UIState())


    init {
        appRepository.userDataStateFlow.onEach {
            it.let {
                direction.moveToGameScreen(appRepository.getName())
            }
        }.launchIn(viewModelScope)
    }

    private fun reduce(block: (UsersScreenContract.UIState) -> UsersScreenContract.UIState) {
                    Log.d("TTT", "model: ")
        val oldValue = uiState.value
        uiState.value = block(oldValue)
    }

    fun loadData() {
        appRepository.userDataStateFlow
            .onEach { list ->
                reduce { it.copy(list = list) }
            }
            .launchIn(viewModelScope)

    }

    override fun onEventDispatcher(intent: UsersScreenContract.Intent) {
        when (intent) {

            UsersScreenContract.Intent.LoadData -> {
                loadData()
            }

            is UsersScreenContract.Intent.EnterUser -> {
                appRepository.attachUser(intent.id, intent.name)
                    .onEach {
                        it.onSuccess {
                            direction.moveToGameScreen(appRepository.getName())
                        }
                    }
                    .launchIn(viewModelScope)

            }


        }
    }


}