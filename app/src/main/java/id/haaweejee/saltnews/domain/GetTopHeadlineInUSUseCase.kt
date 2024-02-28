package id.haaweejee.saltnews.domain

import id.haaweejee.saltnews.data.network.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTopHeadlineInUSUseCase @Inject constructor(private val repository: NewsRepository) {

    suspend operator fun invoke(): Result<List<NewsEntities>> =
        withContext(Dispatchers.IO) {
            kotlin.runCatching {
                repository.loadNews().toListNewsEntities()
            }
        }
}
