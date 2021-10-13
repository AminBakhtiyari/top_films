package me.bakhtiyari.topfilms.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.bakhtiyari.topfilms.domain.repository.ApiRepository
import me.bakhtiyari.topfilms.domain.repositoryImpl.ApiRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModuleBinder {

    @Singleton
    @Binds
    abstract fun bindApiRepository(
        apiRepositoryImpl: ApiRepositoryImpl
    ): ApiRepository
}