package com.example.mypoznan.ui

import com.example.mypoznan.data.LocalRecommendationDataProvider
import com.example.mypoznan.model.Category
import com.example.mypoznan.model.Recommendation

data class MyPoznanUiState(
    val recommendations: List<Recommendation> = listOf(),
    val currentCategory: Category = Category.ALL,
    val currentRecommendation: Recommendation = LocalRecommendationDataProvider.defaultRecommendation,
    val isShowingMainPage: Boolean = true
)
