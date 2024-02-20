package `in`.bpj4.newsapp.presentation.bookmark

import `in`.bpj4.newsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList(),
)