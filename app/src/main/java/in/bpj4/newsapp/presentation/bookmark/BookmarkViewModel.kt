package `in`.bpj4.newsapp.presentation.bookmark

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.bpj4.newsapp.domain.usecases.news.NewsUseCases
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    init {
        getArticles()
    }

    var state = mutableStateOf(BookmarkState())
        private set

    private fun getArticles() {
        newsUseCases.selectArticles().onEach {
            state.value = state.value.copy(articles = it)
        }.launchIn(viewModelScope)
    }
}