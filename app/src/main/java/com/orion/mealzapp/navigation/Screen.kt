package com.orion.mealzapp.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Meals : Screen("meals_screen")
    object Details : Screen("details_screen")
}