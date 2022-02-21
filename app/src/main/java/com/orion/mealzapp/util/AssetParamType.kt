package com.orion.mealzapp.util

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.orion.mealzapp.data.models.Category

class AssetParamType : NavType<Category>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Category? =
        bundle.getParcelable(key)

    override fun parseValue(value: String): Category =
        Gson().fromJson(value, Category::class.java)

    override fun put(bundle: Bundle, key: String, value: Category) =
        bundle.putParcelable(key, value)
}