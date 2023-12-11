package com.example.onlinex_0.di

import com.example.onlinex_0.presentor.screens.game.GameDirection
import com.example.onlinex_0.presentor.screens.game.GameDirectionImpl
import com.example.onlinex_0.presentor.screens.splash.SplashDirection
import com.example.onlinex_0.presentor.screens.splash.SplashDirectionImpl
import com.example.onlinex_0.presentor.screens.start.StartDirection
import com.example.onlinex_0.presentor.screens.start.StartDirectionImpl
import com.example.onlinex_0.presentor.screens.users.UserDirectionImpl
import com.example.onlinex_0.presentor.screens.users.UsersDirection
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DirectionModule {


    @[Binds Singleton]
    fun bindStartDirection(impl: StartDirectionImpl): StartDirection

    @[Binds Singleton]
    fun bindSplashDirection(impl: SplashDirectionImpl): SplashDirection

    @[Binds Singleton]
    fun bindUsersScreen(impl: UserDirectionImpl): UsersDirection

    @[Binds Singleton]
    fun bindGameScreen(impl: GameDirectionImpl): GameDirection


}