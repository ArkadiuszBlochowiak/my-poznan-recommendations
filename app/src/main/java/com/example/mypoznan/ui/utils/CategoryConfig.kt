package com.example.mypoznan.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Museum
import androidx.compose.material.icons.outlined.Park
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mypoznan.R
import com.example.mypoznan.model.Category

object CategoryConfig {

    private val categoryData: Map<Category, Pair<ImageVector, Int>> = mapOf(
        Category.MONUMENT to Pair(Icons.Outlined.Museum, R.string.monument),
        Category.RESTAURANT to Pair(Icons.Outlined.Restaurant, R.string.restaurant),
        Category.PARK to Pair(Icons.Outlined.Park, R.string.park)
    )

    fun getCategoryData(category: Category): Pair<ImageVector, Int> {
       return categoryData[category] ?: Pair(Icons.Outlined.Category, R.string.all)
    }

    fun getAllCategories(): Map<Category, Pair<ImageVector, Int>> {
        return categoryData
    }
}