package com.example.mornhouseproject.di

import com.example.mornhouseproject.network.MaineRepository
import com.example.mornhouseproject.network.MaineRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelBinds {
    @Binds
    abstract fun bindsMainRepositoryBinds(
        maineRepositoryImpl: MaineRepositoryImpl,
    ): MaineRepository
}