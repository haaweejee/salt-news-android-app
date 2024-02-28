package id.haaweejee.saltnews.data.network.service

import id.haaweejee.saltnews.data.network.dtos.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun newsList(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "996c55497a7e444494bccc36ff3cb97a",
    ): NewsResponse
}
