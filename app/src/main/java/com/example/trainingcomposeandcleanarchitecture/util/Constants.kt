package com.example.trainingcomposeandcleanarchitecture.util

import androidx.compose.ui.draw.BuildDrawCacheParams
import androidx.core.os.BuildCompat
import com.example.trainingcomposeandcleanarchitecture.BuildConfig


object Constants {

    const val USER_SETTINGS = "userSettings"
    const val APP_ENTRY = "appEntry"
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://newsapi.org/v2/"
}