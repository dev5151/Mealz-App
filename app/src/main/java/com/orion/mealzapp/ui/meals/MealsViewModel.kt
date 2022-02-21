package com.orion.mealzapp.ui.meals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orion.mealzapp.data.MealsRepository
import com.orion.mealzapp.data.models.Category
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.liveData as liveData

class MealsViewModel(private val repository: MealsRepository) : ViewModel() {
    private val _mealsList = MutableLiveData<List<Category>>()
    val mealsList: LiveData<List<Category>> = _mealsList


    suspend fun getMeals() {
        _mealsList.postValue(repository.getMeals().categories)
    }


}