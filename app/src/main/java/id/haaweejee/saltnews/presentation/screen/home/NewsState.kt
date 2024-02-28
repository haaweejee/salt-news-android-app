package id.haaweejee.saltnews.presentation.screen.home

import id.haaweejee.saltnews.domain.NewsEntities

data class NewsState(
    val isLoading: Boolean,
    val data: List<NewsEntities>,
    val error: Throwable? = null,
) {
    companion object {
        fun initial() = NewsState(
            isLoading = false,
            data = listOf(),
            error = null,
        )
    }
}

interface NewsEvent {

    object GetTopHeadlineNews : NewsEvent

    object GetInitialState : NewsEvent
}
