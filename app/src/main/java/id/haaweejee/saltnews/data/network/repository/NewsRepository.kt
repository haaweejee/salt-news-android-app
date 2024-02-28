package id.haaweejee.saltnews.data.network.repository

import id.haaweejee.saltnews.data.network.dtos.NewsResponse

interface NewsRepository {

    suspend fun loadNews(
        country: String? = null,
    ): NewsResponse
}
