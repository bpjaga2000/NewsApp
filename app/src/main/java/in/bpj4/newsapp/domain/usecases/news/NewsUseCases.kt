package `in`.bpj4.newsapp.domain.usecases.news

class NewsUseCases(
    private val getNews: GetNews,
    private val searchNews: SearchNews,
) {
    fun getNews(sources: List<String>) = getNews.invoke(sources)
    fun searchNews(query: String, sources: List<String>) = searchNews.invoke(query, sources)
}