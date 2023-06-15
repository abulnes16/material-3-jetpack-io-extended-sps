package com.example.to_m3.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.to_m3.ui.screens.details.DetailsScreen
import com.example.to_m3.ui.screens.home.HomeScreen


@Composable
fun ToM3NavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = Home.route, modifier = modifier) {
        composable(Home.route) {
            HomeScreen(
                onTodoClick = { todoId ->
                    navController.navigate("${Details.route}/${todoId}")
                }
            )
        }
        composable(Details.routeWithArgs, arguments = Details.arguments) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt(Details.argName)
            DetailsScreen(todoId = todoId)
        }
    }
}