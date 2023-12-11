package com.example.onlinex_0.di

import com.example.onlinex_0.data.repository.AppRepositoryImpl
import com.example.onlinex_0.domain.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {


    @[Binds Singleton]
    fun bindRepo(impl: AppRepositoryImpl): AppRepository
}