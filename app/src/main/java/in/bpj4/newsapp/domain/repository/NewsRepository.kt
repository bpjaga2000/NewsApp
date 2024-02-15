package `in`.bpj4.newsapp.domain.repository

import androidx.paging.PagingData
import `in`.bpj4.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun searchNews(query: String, sources: List<String>): Flow<PagingData<Article>>
}