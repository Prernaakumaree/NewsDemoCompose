package com.prerna.demo.di

import com.prerna.news.data.apiservice.NewsApiService
import com.prerna.news.data.repository.NewsRepository
import com.prerna.news.data.repository.NewsRepositoryImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object NewsActivityModule {

    @Provides
    @ActivityScoped
    fun provideNewsApiService(): NewsApiService = NewsApiService()

    @Provides
    @ActivityScoped
    fun provideNewsRepo(
        apiService: NewsApiService,
        moshi: Moshi
    ): NewsRepository = NewsRepositoryImpl(apiService, moshi)

    @Provides
    @ActivityScoped
    fun provideMoshi(): Moshi = Moshi.Builder().build()
}
