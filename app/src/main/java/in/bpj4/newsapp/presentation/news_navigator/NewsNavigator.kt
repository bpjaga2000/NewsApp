package `in`.bpj4.newsapp.presentation.news_navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import `in`.bpj4.newsapp.R
import `in`.bpj4.newsapp.domain.model.Article
import `in`.bpj4.newsapp.presentation.bookmark.BookmarkScreen
import `in`.bpj4.newsapp.presentation.bookmark.BookmarkViewModel
import `in`.bpj4.newsapp.presentation.details.DetailsScreen
import `in`.bpj4.newsapp.presentation.details.DetailsViewModel
import `in`.bpj4.newsapp.presentation.home.HomeScreen
import `in`.bpj4.newsapp.presentation.home.HomeViewModel
import `in`.bpj4.newsapp.presentation.navgraph.Route
import `in`.bpj4.newsapp.presentation.news_navigator.componenets.BottomNavigationItem
import `in`.bpj4.newsapp.presentation.news_navigator.componenets.NewsBottomNavigation
import `in`.bpj4.newsapp.presentation.search.SearchScreen
import `in`.bpj4.newsapp.presentation.search.SearchViewModel

@Composable
fun NewsNavigator() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem("Home", R.drawable.ic_home),
            BottomNavigationItem("Search", R.drawable.ic_search),
            BottomNavigationItem("Bookmark", R.drawable.ic_bookmark)
        )
    }

    val navController = rememberNavController()

    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    var isBottomBarVisible by remember {
        mutableStateOf(true)
    }
    isBottomBarVisible = backStackState?.destination?.route != Route.DetailsScreen.route

    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        else -> 0
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible)
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem
                ) { index ->
                    when (index) {
                        0 -> navigateToTab(navController, Route.HomeScreen.route)
                        1 -> navigateToTab(navController, Route.SearchScreen.route)
                        2 -> navigateToTab(navController, Route.BookmarkScreen.route)
                    }
                }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles = articles, navigate = {
                    navigateToTab(navController, Route.SearchScreen.route)
                }, navigateToDetails = { article, _ ->
                    navigateToDetails(navController, article)
                })
            }
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(viewModel.state.value, viewModel::onEvent) { article, _ ->
                    navigateToDetails(navController, article)
                }
            }
            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                BookmarkScreen(viewModel.state.value) { article, _ ->
                    navigateToDetails(navController, article)
                }
            }
            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailsScreen(
                            article = article,
                            event = viewModel::onEvent
                        ) {
                            navController.navigateUp()
                        }
                    }

            }
        }
    }
}

fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { home ->
            popUpTo(home) {
                saveState = true
            }
        }
        restoreState = true
        launchSingleTop = true

    }
}

fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(Route.DetailsScreen.route)
}