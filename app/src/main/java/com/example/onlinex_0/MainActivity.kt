package com.example.onlinex_0

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.example.onlinex_0.presentor.screens.splash.SplashScreen
import com.example.onlinex_0.presentor.screens.start.StartScreen
import com.example.onlinex_0.ui.theme.OnlineX_0Theme
import com.example.onlinex_0.utils.navigator.AppNavigatorHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appNavigatorHandler: AppNavigatorHandler
    @SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            OnlineX_0Theme {
                Navigator(StartScreen()) {navigator ->
                    appNavigatorHandler.uiNavigator.onEach {
                        it.invoke(navigator)
                    }.launchIn(lifecycleScope)
                    CurrentScreen()
                }
            }
        }
    }
}

