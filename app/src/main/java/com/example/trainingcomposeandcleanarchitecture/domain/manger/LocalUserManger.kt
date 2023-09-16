package com.example.trainingcomposeandcleanarchitecture.domain.manger

import kotlinx.coroutines.flow.Flow

interface LocalUserManger {

    suspend fun saveEntry()

    fun readAppEntry(): Flow<Boolean>
}