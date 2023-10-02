package com.example.trainingcomposeandcleanarchitecture.domain.usecases.news

import com.example.trainingcomposeandcleanarchitecture.data.local.NewsDao
import com.example.trainingcomposeandcleanarchitecture.domain.model.Article
import com.example.trainingcomposeandcleanarchitecture.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.getArticles()
    }
}