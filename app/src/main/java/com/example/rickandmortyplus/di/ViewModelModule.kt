package com.example.rickandmortyplus.di

import com.example.rickandmortyplus.api.repository.RMRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    @ViewModelScoped
    abstract fun bindRepository(repo: CharactersRepositoryImpl): RMRepository
}