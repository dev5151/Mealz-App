package com.orion.mealzapp.di

import com.orion.mealzapp.ui.meals.MealsViewModel
import com.orion.mealzapp.data.MealsRepository
import com.orion.mealzapp.data.network.MealsApiInterface
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AppModule {

    val appModule = module {
        single { MealsRepository(get()) }
        viewModel { MealsViewModel(get()) }
    }

    val netModule = module {

        factory {
            Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        single { get<Retrofit>().create(MealsApiInterface::class.java) }
    }


}