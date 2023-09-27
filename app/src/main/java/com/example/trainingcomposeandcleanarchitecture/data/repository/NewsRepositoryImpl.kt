package com.example.trainingcomposeandcleanarchitecture.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.trainingcomposeandcleanarchitecture.data.remote.NewsApi
import com.example.trainingcomposeandcleanarchitecture.data.remote.NewsPagingSource
import com.example.trainingcomposeandcleanarchitecture.data.remote.SearchNewsPagingSource
import com.example.trainingcomposeandcleanarchitecture.domain.model.Article
import com.example.trainingcomposeandcleanarchitecture.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
) : NewsRepository {

    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(newsApi = newsApi, sources = sources.joinToString(separator = ","))
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(searchQuery= searchQuery, newsApi = newsApi, sources = sources.joinToString(separator = ","))
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteArticle(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getArticles(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}