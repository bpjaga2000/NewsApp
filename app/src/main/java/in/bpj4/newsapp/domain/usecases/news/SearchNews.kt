package `in`.bpj4.newsapp.domain.usecases.news

import `in`.bpj4.newsapp.domain.repository.NewsRepository

class SearchNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(query: String, sources: List<String>) =
        newsRepository.searchNews(query, sources)

}