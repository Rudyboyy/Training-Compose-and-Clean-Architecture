package com.example.trainingcomposeandcleanarchitecture.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.trainingcomposeandcleanarchitecture.data.local.NewsDao
import com.example.trainingcomposeandcleanarchitecture.data.remote.NewsApi
import com.example.trainingcomposeandcleanarchitecture.data.remote.NewsPagingSource
import com.example.trainingcomposeandcleanarchitecture.data.remote.SearchNewsPagingSource
import com.example.trainingcomposeandcleanarchitecture.domain.model.Article
import com.example.trainingcomposeandcleanarchitecture.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
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
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun getArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun getArticle(url: String): Article? {
        return newsDao.getArticle(url)
    }
}