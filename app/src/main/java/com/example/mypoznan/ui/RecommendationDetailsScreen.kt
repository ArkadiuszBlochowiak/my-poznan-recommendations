package com.example.mypoznan.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun RecommendationDetailsScreen(
    uiState: MyPoznanUiState,
    onBackEvent: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackEvent()
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inverseOnSurface),
    ) {
        item {
            RecommendationDetails(
                item = uiState.currentRecommendation
            )
        }
    }
}