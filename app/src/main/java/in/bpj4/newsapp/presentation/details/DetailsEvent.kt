package `in`.bpj4.newsapp.presentation.details

import `in`.bpj4.newsapp.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    data object RemoveSideEffect : DetailsEvent()
}