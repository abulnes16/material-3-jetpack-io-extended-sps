package com.example.to_m3.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface ToM3Destinations {
    val route: String
    val title: String
}

object Home: ToM3Destinations {
     override val route: String = "home"
    override val title: String = "To-M3"

}

object  Details: ToM3Destinations {
    override val route: String = "details"
    override val title: String = "To-Do Details"
    const val routeWithArgs: String = "details/{todoId}"
    const val argName = "todoId"
    val arguments = listOf(
        navArgument("todoId"){
            type = NavType.IntType
        }
    )
}
