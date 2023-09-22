package com.example.trainingcomposeandcleanarchitecture.data.remote.dto

import com.example.trainingcomposeandcleanarchitecture.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)