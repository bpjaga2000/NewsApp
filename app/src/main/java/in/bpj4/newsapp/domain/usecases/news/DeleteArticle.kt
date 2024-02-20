package `in`.bpj4.newsapp.domain.usecases.news

import `in`.bpj4.newsapp.data.local.NewsDao
import `in`.bpj4.newsapp.domain.model.Article

class DeleteArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article) = newsDao.delete(article)

}