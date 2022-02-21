package com.orion.mealzapp.data

import com.orion.mealzapp.data.models.Category
import com.orion.mealzapp.data.models.MealModel
import com.orion.mealzapp.data.network.MealsApiInterface

class MealsRepository(private val mealsApiInterface: MealsApiInterface) {
    suspend fun getMeals(): MealModel = mealsApiInterface.getMeals()
}