package `in`.bpj4.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import `in`.bpj4.newsapp.data.remote.NewsApi
import `in`.bpj4.newsapp.data.remote.NewsPagingSource
import `in`.bpj4.newsapp.data.remote.SearchPagingSource
import `in`.bpj4.newsapp.domain.model.Article
import `in`.bpj4.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi, sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(query: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            PagingConfig(10),
            pagingSourceFactory = {
                SearchPagingSource(
                    newsApi,
                    query, sources.joinToString(",")
                )
            }
        ).flow
    }

}