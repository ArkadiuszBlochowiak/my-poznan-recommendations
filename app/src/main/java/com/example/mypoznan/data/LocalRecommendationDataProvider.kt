package com.example.mypoznan.data

import android.content.res.Resources
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import com.example.mypoznan.R
import com.example.mypoznan.model.Category
import com.example.mypoznan.model.Recommendation

object LocalRecommendationDataProvider {

    private val dataList = listOf(
        Recommendation(
            id = 1L,
            titleRes = R.string.malta,
            descriptionRes = R.string.park_description,
            category = Category.PARK,
            rating = 3
        ),
        Recommendation(
            id = 2L,
            titleRes = R.string.solacz,
            descriptionRes = R.string.park_description,
            category = Category.PARK,
            rating = 5
        ),
        Recommendation(
            id = 3L,
            titleRes = R.string.town_hall,
            descriptionRes = R.string.monument_description,
            category = Category.MONUMENT,
            rating = 4
        ),
        Recommendation(
            id = 4L,
            titleRes = R.string.cytadela,
            descriptionRes = R.string.park_description,
            category = Category.PARK,
            rating = 5
        ),
        Recommendation(
            id = 5L,
            titleRes = R.string.ming_wok,
            descriptionRes = R.string.restaurant_description,
            category = Category.RESTAURANT,
            rating = 4
        ),
        Recommendation(
            id = 6L,
            titleRes = R.string.national_museum,
            descriptionRes = R.string.monument_description,
            category = Category.MONUMENT,
            rating = 4
        ),
        Recommendation(
            id = 7L,
            titleRes = R.string.stary_mlyn,
            descriptionRes = R.string.restaurant_description,
            category = Category.RESTAURANT,
            rating = 5
        ),
        Recommendation(
            id = 8L,
            titleRes = R.string.old_zoo,
            descriptionRes = R.string.monument_description,
            category = Category.MONUMENT,
            rating = 3
        ),
        Recommendation(
            id = 9L,
            titleRes = R.string.porta,
            descriptionRes = R.string.monument_description,
            category = Category.MONUMENT,
            rating = 4
        ),
        Recommendation(
            id = 10L,
            titleRes = R.string.thali,
            descriptionRes = R.string.restaurant_description,
            category = Category.RESTAURANT,
            rating = 4
        ),
        Recommendation(
            id = 11L,
            titleRes = R.string.pasta_disco,
            descriptionRes = R.string.restaurant_description,
            category = Category.RESTAURANT,
            rating = 4
        ),
        Recommendation(
            id = 12L,
            titleRes = R.string.kim_chi_ken,
            descriptionRes = R.string.restaurant_description,
            category = Category.RESTAURANT,
            rating = 3
        ),
    )

    val defaultRecommendation = dataList[0]

    fun getAllRecommendations(resources: Resources): List<Recommendation> {
        return dataList.sortedBy { resources.getString(it.titleRes)}
    }

    fun getCategoryRecommendations(category: Category, resources: Resources): List<Recommendation> {
        return getAllRecommendations(resources).filter {
            it.category == category
        }
    }
}