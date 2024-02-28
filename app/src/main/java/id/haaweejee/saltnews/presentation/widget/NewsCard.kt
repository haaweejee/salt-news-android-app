package id.haaweejee.saltnews.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import id.haaweejee.saltnews.R
import id.haaweejee.saltnews.domain.NewsEntities
import id.haaweejee.saltnews.presentation.ext.toFormattedDate
import id.haaweejee.saltnews.presentation.navigation.Navigation
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NewsCard(
    navController: NavHostController,
    data: NewsEntities,
) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFF4141),
            contentColor = Color.White,
        ),
        onClick = {
            val encodedUrl = URLEncoder.encode(data.newsUrl, StandardCharsets.UTF_8.toString())
            navController.navigate(
                Navigation.ReadNews.createRoute(
                    source = data.newsSource,
                    newsUrl = encodedUrl,
                ),
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            if (data.imageUrl.isEmpty()) {
                Image(
                    painter = painterResource(id = R.drawable.news_placeholder),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                )
            } else {
                AsyncImage(
                    model = data.imageUrl,
                    contentDescription = "",
                    placeholder = painterResource(id = R.drawable.news_placeholder),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                )
            }

            Column(
                modifier = Modifier.padding(horizontal = 8.dp),
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = data.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = data.description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = data.publishedDate.toFormattedDate(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.End,
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsCardPreview() {
    NewsCard(
        navController = rememberNavController(),
        data = NewsEntities(
            "Sony Announces Significant PlayStation Layoffs Affecting 900 Staff, London Studio to Close - IGN",
            "Sony has announced a significant round of layoffs affecting around 900 staff, or about 8% of its global workforce.",
            "https://assets-prd.ignimgs.com/2024/02/27/sony-1709040639732.jpg?width=1280",
            "2024-02-27T16:37:05Z",
            "",
            "",
        ),
    )
}
