package id.haaweejee.saltnews.presentation.navigation

sealed class Navigation(val route: String) {
    object Home : Navigation("home")
    object ReadNews : Navigation(route = "readNews/{source}/{newsUrl}") {
        fun createRoute(source: String, newsUrl: String) = "readNews/$source/$newsUrl"
    }
}
