package `in`.bpj4.newsapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.bpj4.newsapp.domain.usecases.news.NewsUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    var sideEffects by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.selectArticle(event.article.url)
                    if (article == null) {
                        newsUseCases.upsertArticle(event.article)
                        sideEffects = "Article saved"
                    } else {
                        newsUseCases.deleteArticle(event.article)
                        sideEffects = "Article removed"
                    }
                }
            }

            DetailsEvent.RemoveSideEffect -> {
                sideEffects = null
            }
        }
    }

}