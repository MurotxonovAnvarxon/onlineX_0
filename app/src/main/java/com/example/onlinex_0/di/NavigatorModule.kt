package com.example.onlinex_0.di

import com.example.onlinex_0.utils.navigator.AppNavigator
import com.example.onlinex_0.utils.navigator.AppNavigatorDispatcher
import com.example.onlinex_0.utils.navigator.AppNavigatorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface NavigatorModule {

    @[Binds Singleton]
    fun bindAppHa(impl: AppNavigatorDispatcher): AppNavigatorHandler

    @[Binds Singleton]
    fun bindApp(impl: AppNavigatorDispatcher): AppNavigator
}