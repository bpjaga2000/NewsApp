package `in`.bpj4.newsapp.domain.usecases.news

import `in`.bpj4.newsapp.data.local.NewsDao
import `in`.bpj4.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticle(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(url: String): Article? = newsDao.getArticle(url)
}