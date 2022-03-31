package com.kdw.mystorymedia.di

import com.kdw.mystorymedia.repository.AuthRepository
import com.kdw.mystorymedia.repository.impl.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @ActivityScoped
    @Provides
    fun provideAuthRepository() = AuthRepositoryImpl() as AuthRepository
}
