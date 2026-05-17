package com.example.mypoznan.ui.utils

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mypoznan.model.Category

data class NavigationItem(
    val category: Category,
    val text: Int,
    val icon: ImageVector
)

object NavigationItemList {
    private val list = buildList {
        CategoryConfig.getAllCategories().forEach { (category, pair) ->
            this += NavigationItem(
                category = category,
                text = pair.second,
                icon = pair.first,
            )
        }
    }

    fun getNavigationList(): List<NavigationItem> {
        return list
    }
}
