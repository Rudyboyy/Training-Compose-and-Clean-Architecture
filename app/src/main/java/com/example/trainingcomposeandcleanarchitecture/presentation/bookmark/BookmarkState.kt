package com.example.trainingcomposeandcleanarchitecture.presentation.bookmark

import com.example.trainingcomposeandcleanarchitecture.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)