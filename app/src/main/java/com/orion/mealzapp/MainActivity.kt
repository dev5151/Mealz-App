package com.orion.mealzapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.orion.mealzapp.ui.theme.MealzAppTheme
import androidx.navigation.compose.rememberNavController
import com.orion.mealzapp.navigation.SetupNavGraph
import com.orion.mealzapp.ui.meals.MealsViewModel
import kotlinx.coroutines.*
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {

    private val mealsViewModel: MealsViewModel by viewModel()
    private val job = SupervisorJob()
    private val ioScope by lazy { CoroutineScope(job + Dispatchers.IO) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            LaunchedEffect(key1 = true) {
                ioScope.launch(Dispatchers.IO) {
                    mealsViewModel.getMeals()
                }
            }

            val navController = rememberNavController()
            SetupNavGraph(navController = navController, mealsViewModel)
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealzAppTheme {
        Greeting("Android")
    }
}

