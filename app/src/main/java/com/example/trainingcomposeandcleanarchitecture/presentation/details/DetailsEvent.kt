package com.example.trainingcomposeandcleanarchitecture.presentation.details

sealed class DetailsEvent {
    object SaveArticle : DetailsEvent()
}