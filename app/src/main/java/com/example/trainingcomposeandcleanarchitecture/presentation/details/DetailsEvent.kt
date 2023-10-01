package com.example.trainingcomposeandcleanarchitecture.presentation.details

import com.example.trainingcomposeandcleanarchitecture.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()
}