package `in`.bpj4.newsapp.domain.usecases.news

import `in`.bpj4.newsapp.domain.model.Article

class NewsUseCases(
    private val getNews: GetNews,
    private val searchNews: SearchNews,
    private val upsertArticle: UpsertArticle,
    private val deleteArticle: DeleteArticle,
    private val selectArticles: SelectArticles,
    private val selectArticle: SelectArticle
) {
    fun getNews(sources: List<String>) = getNews.invoke(sources)
    fun searchNews(query: String, sources: List<String>) = searchNews.invoke(query, sources)
    suspend fun upsertArticle(article: Article) = upsertArticle.invoke(article)
    suspend fun deleteArticle(article: Article) = deleteArticle.invoke(article)
    fun selectArticles() = selectArticles.invoke()
    suspend fun selectArticle(url: String) = selectArticle.invoke(url)
}