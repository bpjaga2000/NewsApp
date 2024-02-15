package `in`.bpj4.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import `in`.bpj4.newsapp.domain.model.Article
import `in`.bpj4.newsapp.util.Constants.API_KEY

class SearchPagingSource(
    private val newsApi: NewsApi,
    private val query: String,
    private val sources: String
) : PagingSource<Int, Article>() {

    private var totalArticles = 0
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            val st = state.closestPageToPosition(it)
            st?.prevKey?.plus(1) ?: st?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val response = newsApi.searchNews(query, page, sources, API_KEY)
            totalArticles += response.articles.size
            LoadResult.Page(
                response.articles.distinctBy { it.title },
                if (page > 1) page - 1 else null,
                if (totalArticles >= response.totalResults) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(
                e
            )
        }
    }
}