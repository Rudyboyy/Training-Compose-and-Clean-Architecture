package com.example.trainingcomposeandcleanarchitecture.domain.usecases

import com.example.trainingcomposeandcleanarchitecture.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManger: LocalUserManger
) {
    operator fun invoke(): Flow<Boolean> {
        return localUserManger.readAppEntry()
    }
}