package `in`.bpj4.newsapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import `in`.bpj4.newsapp.R
import `in`.bpj4.newsapp.domain.model.Article
import `in`.bpj4.newsapp.domain.model.Source
import `in`.bpj4.newsapp.presentation.Dimens.ArticleCardSize
import `in`.bpj4.newsapp.presentation.Dimens.ExtraSmallPadding
import `in`.bpj4.newsapp.presentation.Dimens.ExtraSmallPadding2
import `in`.bpj4.newsapp.presentation.Dimens.SmallIconSize
import `in`.bpj4.newsapp.ui.theme.NewsAppTheme

@Composable
fun ArticleCard(modifier: Modifier = Modifier, article: Article, onClick: (Article) -> Unit) {

    val context = LocalContext.current

    Row(modifier = modifier
        .padding(4.dp)
        .clickable { onClick(article) }) {
        AsyncImage(
            modifier = Modifier.size(ArticleCardSize),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = article.title,
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding)
                .height(ArticleCardSize)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.text_medium
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.text_medium
                    )
                )

                Spacer(modifier = Modifier.width(ExtraSmallPadding2))

                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.size(SmallIconSize),
                    tint = colorResource(
                        id = R.color.black
                    )
                )

                Spacer(modifier = Modifier.width(ExtraSmallPadding2))

                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.text_medium
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ArtileCardPreview() {
    NewsAppTheme {
        ArticleCard(
            article = Article(
                author = "ore",
                content = "shine",
                description = "ore was shindha desu, kawaranai. mochiron ore no atama ",
                publishedAt = "ima",
                source = Source(id = "ichiban", "bbc-news"),
                title = "ore ga shinda",
                url = "hehe",
                urlToImage = "hehehehe"
            )
        ) {

        }
    }
}