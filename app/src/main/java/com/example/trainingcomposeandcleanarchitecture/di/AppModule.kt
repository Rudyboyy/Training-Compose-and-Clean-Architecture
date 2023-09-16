package com.example.trainingcomposeandcleanarchitecture.di

import android.app.Application
import androidx.core.view.WindowInsetsCompat.Type.InsetsType
import com.example.trainingcomposeandcleanarchitecture.data.manger.LocalUserMangerImpl
import com.example.trainingcomposeandcleanarchitecture.domain.manger.LocalUserManger
import com.example.trainingcomposeandcleanarchitecture.domain.usecases.AppEntryUseCases
import com.example.trainingcomposeandcleanarchitecture.domain.usecases.ReadAppEntry
import com.example.trainingcomposeandcleanarchitecture.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}