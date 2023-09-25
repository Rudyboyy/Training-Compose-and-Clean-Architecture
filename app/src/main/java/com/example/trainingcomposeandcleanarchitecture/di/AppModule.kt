package com.example.trainingcomposeandcleanarchitecture.di

import android.app.Application
import com.example.trainingcomposeandcleanarchitecture.data.manger.LocalUserMangerImpl
import com.example.trainingcomposeandcleanarchitecture.data.remote.NewsApi
import com.example.trainingcomposeandcleanarchitecture.data.remote.repository.NewsRepositoryImpl
import com.example.trainingcomposeandcleanarchitecture.domain.manger.LocalUserManger
import com.example.trainingcomposeandcleanarchitecture.domain.repository.NewsRepository
import com.example.trainingcomposeandcleanarchitecture.domain.usecases.app_entry.AppEntryUseCases
import com.example.trainingcomposeandcleanarchitecture.domain.usecases.app_entry.ReadAppEntry
import com.example.trainingcomposeandcleanarchitecture.domain.usecases.app_entry.SaveAppEntry
import com.example.trainingcomposeandcleanarchitecture.domain.usecases.news.GetNews
import com.example.trainingcomposeandcleanarchitecture.domain.usecases.news.NewsUseCases
import com.example.trainingcomposeandcleanarchitecture.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManger = LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )
    @Provides
    @Singleton
    fun provideApiInstance(): NewsApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
        )
    }
}