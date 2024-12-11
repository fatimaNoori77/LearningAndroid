package ir.noori.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.noori.data.ApiService
import ir.noori.data.RepositoryImpl
import ir.noori.domain.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): Repository {
        return RepositoryImpl(apiService)
    }
}
