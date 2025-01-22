package com.example.mynewsroom.presentation_layer.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mynewsroom.presentation_layer.articleScreen.ArticleScreen
import com.example.mynewsroom.presentation_layer.news_screen.NewsScreen
import com.example.mynewsroom.presentation_layer.news_screen.NewsScreenViewModel

@Composable
fun NavGraphSetup(
    navHostController: NavHostController
) {
    val argKey = "web_url"
    NavHost(
        navController = navHostController,
        startDestination = "news_screen"
    ) {
        composable(route = "news_screen") {
            val viewModel: NewsScreenViewModel = hiltViewModel()
            NewsScreen(
                state = viewModel.state,
                onEvent = viewModel::onEvent,
                onReadFullStoryButtonClicked = { url ->
                    navHostController.navigate("article_screen?$argKey=$url")
                }
            )
        }
        composable(
            route = "article_screen?$argKey={$argKey}",
            arguments = listOf(navArgument(name = argKey){
                type = NavType.StringType
            })
        ) { backStackEntry ->
            ArticleScreen(
                url = backStackEntry.arguments?.getString(argKey),
                onBackPress = { navHostController.navigateUp()})
        }
    }
}



