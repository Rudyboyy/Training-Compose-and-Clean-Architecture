package com.example.trainingcomposeandcleanarchitecture.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.trainingcomposeandcleanarchitecture.presentation.bookmark.BookmarkScreen
import com.example.trainingcomposeandcleanarchitecture.presentation.bookmark.BookmarkViewModel
import com.example.trainingcomposeandcleanarchitecture.presentation.news_navigator.NewsNavigator
import com.example.trainingcomposeandcleanarchitecture.presentation.onboarding.OnBoardingScreen
import com.example.trainingcomposeandcleanarchitecture.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route) {
//                val viewModel: HomeViewModel = hiltViewModel()
//                val articles = viewModel.news.collectAsLazyPagingItems()
//                HomeScreen(articles = articles, navigate = { /*TODO*/ })
//                val viewModel: SearchViewModel = hiltViewModel()
//                SearchScreen(state = viewModel.state.value, event = viewModel::onEvent, navigate = {})
//                val viewModel: BookmarkViewModel = hiltViewModel()
//                BookmarkScreen(state = viewModel.state.value, navigateToDetails = {})
                NewsNavigator()
            }
        }
    }
}