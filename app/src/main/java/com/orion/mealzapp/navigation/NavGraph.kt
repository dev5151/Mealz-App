package com.orion.mealzapp.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.orion.mealzapp.data.models.Category
import com.orion.mealzapp.ui.meals.MealScreen
import com.orion.mealzapp.ui.meals.MealsViewModel
import com.orion.mealzapp.ui.splash.AnimatedSplashScreen
import androidx.navigation.compose.composable
import com.orion.mealzapp.ui.detail.MealsDetailScreen
import com.orion.mealzapp.util.AssetParamType


@Composable
fun SetupNavGraph(
    navController: NavHostController,
    mealViewModel: MealsViewModel
) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen(navController)
        }
        composable(route = Screen.Meals.route) {
            MealScreen(viewModel = mealViewModel) {
                val meal = Uri.encode(Gson().toJson(it))
                navController.navigate("${Screen.Details.route}/$meal")
            }
        }
        composable(
            route = "${Screen.Details.route}/{meal}",
            arguments = listOf(navArgument("meal") { type = AssetParamType() })
        ) {
            val meal = it.arguments?.getParcelable<Category>("meal")
            MealsDetailScreen(meal) { navController.navigateUp() }
        }
    }
}
