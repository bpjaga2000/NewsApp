package `in`.bpj4.newsapp.presentation.navgraph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import `in`.bpj4.newsapp.presentation.home.HomeScreen
import `in`.bpj4.newsapp.presentation.home.HomeViewModel
import `in`.bpj4.newsapp.presentation.onboarding.OnBoardingScreen
import `in`.bpj4.newsapp.presentation.onboarding.OnBoardingViewModel
import `in`.bpj4.newsapp.presentation.search.SearchScreen
import `in`.bpj4.newsapp.presentation.search.SearchViewModel


@Composable
fun NavGraph(startDestination: String) {


    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                    OnBoardingScreen(viewModel::onEvent)
                }
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigationScreen.route
        ) {
            composable(
                route = Route.NewsNavigationScreen.route
            ) {
                /*val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles = articles, navigate = {
                })*/

                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(state =  viewModel.state.value, event = viewModel::onEvent, navigate = {})
            }
        }
    }

}