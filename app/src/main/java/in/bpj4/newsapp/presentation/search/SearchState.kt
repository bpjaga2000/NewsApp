package `in`.bpj4.newsapp.presentation.search

import androidx.paging.PagingData
import `in`.bpj4.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)