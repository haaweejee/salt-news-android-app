package id.haaweejee.saltnews.data.network.repository

import id.haaweejee.saltnews.data.network.dtos.NewsResponse
import id.haaweejee.saltnews.data.network.service.NewsService
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val service: NewsService,
) : NewsRepository {
    override suspend fun loadNews(country: String?): NewsResponse = service.newsList()
}
