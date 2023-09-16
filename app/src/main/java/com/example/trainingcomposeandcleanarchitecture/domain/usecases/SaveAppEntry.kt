package com.example.trainingcomposeandcleanarchitecture.domain.usecases

import com.example.trainingcomposeandcleanarchitecture.domain.manger.LocalUserManger

class SaveAppEntry(
    private val localUserManger: LocalUserManger
) {
    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }
}