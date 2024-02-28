package id.haaweejee.saltnews.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.haaweejee.saltnews.presentation.navigation.Navigation
import id.haaweejee.saltnews.presentation.screen.home.NewsScreen
import id.haaweejee.saltnews.presentation.screen.ratednews.ReadNewsScreen

@Composable
fun NewsApp() {
    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Navigation.Home.route,
    ) {
        composable(route = Navigation.Home.route) {
            NewsScreen(navController)
        }
        composable(
            route = Navigation.ReadNews.route,
            arguments = listOf(
                navArgument("newsUrl") { type = NavType.StringType },
                navArgument("source") { type = NavType.StringType },
            ),
        ) {
            val newsUrl = it.arguments?.getString("newsUrl")
            val source = it.arguments?.getString("source")
            if (newsUrl != null && source != null) {
                ReadNewsScreen(
                    navController = navController,
                    url = newsUrl,
                    source = source,
                )
            }
        }
    }
}
