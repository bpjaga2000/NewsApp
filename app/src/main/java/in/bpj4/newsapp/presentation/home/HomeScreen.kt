package `in`.bpj4.newsapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import `in`.bpj4.newsapp.R
import `in`.bpj4.newsapp.domain.model.Article
import `in`.bpj4.newsapp.presentation.Dimens.MediumPadding1
import `in`.bpj4.newsapp.presentation.common.ArticlesList
import `in`.bpj4.newsapp.presentation.common.SearchBar
import `in`.bpj4.newsapp.presentation.navgraph.Route

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigate: (String) -> Unit,
    navigateToDetails: (Article, String) -> Unit
) {

    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items.slice(IntRange(0, 9))
                    .joinToString(separator = " \uD83d\uDFE5") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MediumPadding1)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        SearchBar(
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = { navigate(Route.SearchScreen.route) },
            onSearch = {}
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1)
                .basicMarquee(),
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticlesList(
            modifier = Modifier,//.padding(horizontal = MediumPadding1),
            articles = articles,
            onClick = {
                navigateToDetails(it, Route.DetailsScreen.route)
            }
        )
    }
}