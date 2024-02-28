package id.haaweejee.saltnews.data.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.haaweejee.saltnews.data.network.repository.NewsRepository
import id.haaweejee.saltnews.data.network.repository.NewsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsRepository(
        repositoryImpl: NewsRepositoryImpl
    ): NewsRepository
}