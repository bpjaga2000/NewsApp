package `in`.bpj4.newsapp.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import `in`.bpj4.newsapp.domain.model.Article
import `in`.bpj4.newsapp.presentation.Dimens.MediumPadding1
import `in`.bpj4.newsapp.presentation.common.ArticlesList
import `in`.bpj4.newsapp.presentation.navgraph.Route

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigate: (Article, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = MediumPadding1, end = MediumPadding1, start = MediumPadding1)
    ) {
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticlesList(
            articles = state.articles,
            onClick = { navigate(it, Route.DetailsScreen.route) })
    }
}