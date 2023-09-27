package com.example.trainingcomposeandcleanarchitecture.domain.usecases.app_entry

import com.example.trainingcomposeandcleanarchitecture.domain.manger.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}