package com.orion.mealzapp.data.network

import com.orion.mealzapp.data.models.Category
import com.orion.mealzapp.data.models.MealModel
import retrofit2.Call
import retrofit2.http.GET

interface MealsApiInterface {

    @GET("api/json/v1/1/categories.php")
    suspend fun getMeals(): MealModel
}