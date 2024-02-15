package `in`.bpj4.newsapp.data.remote

import `in`.bpj4.newsapp.data.remote.dto.NewsResponse
import `in`.bpj4.newsapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsResponse
}