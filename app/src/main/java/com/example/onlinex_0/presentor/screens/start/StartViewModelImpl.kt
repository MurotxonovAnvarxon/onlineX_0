package com.example.onlinex_0.presentor.screens.start

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
class StartViewModelImpl @Inject constructor(
    private val direction: StartDirection,
    private val repository: AppRepository
) : ViewModel(), StartContract.ViewModel {
    override val uiState = MutableStateFlow<StartContract.UIState>(StartContract.UIState())
    private var name = ""

    private fun reduce(block: (StartContract.UIState) -> StartContract.UIState) {
        val oldValue = uiState.value
        uiState.value = block(oldValue)
    }

    override fun onEventDispatcher(intent: StartContract.Intent) {
        when (intent) {

//            is StartContract.Intent.SaveName -> {
//                name = intent.name
//                reduce { it.copy(name = name) }
//            }

           is StartContract.Intent.SaveName -> {

               name = intent.name
               reduce { it.copy(name = name)}

                repository.addUser(name).onEach {
                    it.onSuccess {
                        direction.moveToUsers()
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}