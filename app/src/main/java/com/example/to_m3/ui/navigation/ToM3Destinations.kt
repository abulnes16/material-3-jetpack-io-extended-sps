package com.example.to_m3.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument


object Home {
    const val route: String = "home"
}

object  Details {
    const val routeWithArgs: String = "details/{todoId}"
    const val argName = "todoId"
    val arguments = listOf(
        navArgument("todoId"){
            type = NavType.StringType
        }
    )
}