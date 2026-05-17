package com.example.mypoznan.data

import androidx.compose.ui.res.stringResource
import com.example.mypoznan.R
import com.example.mypoznan.model.Category
import com.example.mypoznan.model.Recommendation

object LocalRecommendationDataProvider {
    val defaultRecommendation = Recommendation(
        1, R.string.monument, R.string.monument_description, Category.MONUMENT, 0
    )

    fun getAllRecommendations(): List<Recommendation> {
        return listOf(
            Recommendation(
                id = 1L,
                titleRes = R.string.malta,
                descriptionRes = R.string.park_description,
                category = Category.PARK,
                rating = 4
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
            )
        )
    }
}