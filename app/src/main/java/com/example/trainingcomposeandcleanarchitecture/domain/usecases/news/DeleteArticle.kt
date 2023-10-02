package com.example.trainingcomposeandcleanarchitecture.domain.usecases.news

import com.example.trainingcomposeandcleanarchitecture.data.local.NewsDao
import com.example.trainingcomposeandcleanarchitecture.domain.model.Article
import com.example.trainingcomposeandcleanarchitecture.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteArticle @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article = article)
    }
}