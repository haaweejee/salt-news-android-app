package id.haaweejee.saltnews.domain

import id.haaweejee.saltnews.data.network.dtos.NewsResponse

fun NewsResponse.toListNewsEntities(): List<NewsEntities> =
    this.articles?.map {
        NewsEntities(
            title = it?.title.orEmpty(),
            description = it?.description.orEmpty(),
            imageUrl = it?.urlToImage.orEmpty(),
            publishedDate = it?.publishedAt.orEmpty(),
            newsUrl = it?.url.orEmpty(),
            newsSource = it?.source?.name.orEmpty()
        )
    } ?: listOf()