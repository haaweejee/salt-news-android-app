package id.haaweejee.saltnews.presentation.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.haaweejee.saltnews.presentation.widget.ErrorScreen
import id.haaweejee.saltnews.presentation.widget.LoadingProgress
import id.haaweejee.saltnews.presentation.widget.NewsCard
import id.haaweejee.saltnews.presentation.widget.NewsTopAppBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun NewsScreen(
    navController: NavHostController,
    viewModel: NewsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    var isRefreshing by remember { mutableStateOf(false) }

    val pullToRefresh = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            viewModel.event(NewsEvent.GetInitialState)
            viewModel.event(
                NewsEvent.GetTopHeadlineNews,
            )
        },
    )

    LaunchedEffect(state.data) {
        viewModel.event(NewsEvent.GetInitialState)
        viewModel.event(NewsEvent.GetTopHeadlineNews)
    }

    Scaffold(
        topBar = {
            NewsTopAppBar(title = "NewsApp")
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullToRefresh),
        ) {
            if (state.error != null) {
                ErrorScreen {
                    viewModel.event(NewsEvent.GetTopHeadlineNews)
                }
            }

            if (state.isLoading) {
                LoadingProgress()
            } else {
                LazyColumn(
                    contentPadding = it,
                ) {
                    items(state.data) { data ->
                        NewsCard(
                            navController,
                            data = data,
                        )
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullToRefresh,
                modifier = Modifier.align(Alignment.TopCenter),
            )
        }
    }
}
