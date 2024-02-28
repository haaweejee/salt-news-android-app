package id.haaweejee.saltnews.presentation.screen.ratednews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import id.haaweejee.saltnews.presentation.widget.NewsTopAppBar
import id.haaweejee.saltnews.presentation.widget.NewsWebView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadNewsScreen(
    navController: NavHostController,
    url: String,
    source: String,
) {
    Scaffold(
        topBar = {
            NewsTopAppBar(
                title = source,
                navController = navController
            )
        },
    ) {
        Column(modifier = Modifier.padding(it)) {
            NewsWebView(url = url)
        }
    }
}
