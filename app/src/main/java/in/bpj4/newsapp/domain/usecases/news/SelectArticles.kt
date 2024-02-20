package `in`.bpj4.newsapp.domain.usecases.news

import `in`.bpj4.newsapp.data.local.NewsDao

class SelectArticles(
    private val newsDao: NewsDao
) {

    operator fun invoke() = newsDao.getArticles()
}