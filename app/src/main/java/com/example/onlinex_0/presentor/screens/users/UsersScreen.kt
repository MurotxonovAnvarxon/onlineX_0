package com.example.onlinex_0.presentor.screens.users

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.onlinex_0.presentor.components.UserItem

class UsersScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        Log.d("AAA", "Content: UsersScreen ")

        val viewModel: UsersScreenContract.ViewModel = getViewModel<UserScreenViewModel>()
        UsersScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher
        )
    }
}

@Composable
fun UsersScreenContent(
    uiState: State<UsersScreenContract.UIState>,
    onEventDispatcher: (UsersScreenContract.Intent) -> Unit
) {
    onEventDispatcher(UsersScreenContract.Intent.LoadData)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(uiState.value.list) {
                UserItem(it) { id, name ->
                    onEventDispatcher(UsersScreenContract.Intent.EnterUser(id, name))
                }
                Spacer(modifier = Modifier.size(12.dp))
            }
            item {
                Spacer(modifier = Modifier.size(12.dp))
            }
        }
    }
}
