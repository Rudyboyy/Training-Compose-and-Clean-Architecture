package com.example.trainingcomposeandcleanarchitecture.di

import android.app.Application
import androidx.room.Room
import com.example.trainingcomposeandcleanarchitecture.data.local.NewsDao
import com.example.trainingcomposeandcleanarchitecture.data.local.NewsDatabase
import com.example.trainingcomposeandcleanarchitecture.data.local.NewsTypeConvertor
import com.example.trainingcomposeandcleanarchitecture.data.manager.LocalUserManagerImpl
import com.example.trainingcomposeandcleanarchitecture.data.remote.NewsApi
import com.example.trainingcomposeandcleanarchitecture.data.repository.NewsRepositoryImpl
import com.example.trainingcomposeandcleanarchitecture.domain.manger.LocalUserManager
import com.example.trainingcomposeandcleanarchitecture.domain.repository.NewsRepository
import com.example.trainingcomposeandcleanarchitecture.domain.usecases.app_entry.AppEntryUseCases
import com.example.trainingcomposeandcleanarchitecture.domain.usecases.app_entry.ReadAppEntry
import com.example.trainingcomposeandcleanarchitecture.domain.usecases.app_entry.SaveAppEntry
import com.example.trainingcomposeandcleanarchitecture.domain.usecases.news.DeleteArticle
import com.example.trainingcomposeandcleanarchitecture.domain.usecases.news.GetNews
import com.example.trainingcomposeandcleanarchitecture.domain.usecases.news.NewsUseCases
import com.example.trainingcomposeandcleanarchitecture.domain.usecases.news.SearchNews
import com.example.trainingcomposeandcleanarchitecture.domain.usecases.news.SelectArticles
import com.example.trainingcomposeandcleanarchitecture.domain.usecases.news.UpsertArticle
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
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
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
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsetArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            selectArticles = SelectArticles(newsDao)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = "news_db"
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}