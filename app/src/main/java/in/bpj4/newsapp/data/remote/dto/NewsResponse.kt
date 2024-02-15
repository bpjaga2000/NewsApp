package `in`.bpj4.newsapp.data.remote.dto

import `in`.bpj4.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)