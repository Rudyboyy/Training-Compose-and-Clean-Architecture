package com.example.trainingcomposeandcleanarchitecture.domain.usecases.app_entry

import com.example.trainingcomposeandcleanarchitecture.domain.manger.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}