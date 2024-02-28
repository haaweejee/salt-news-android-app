package id.haaweejee.saltnews.data.network.dtos

data class NewsResponse(
    val articles: List<Article?>? = null,
    val status: String? = null,
    val totalResults: Int? = null,
)
