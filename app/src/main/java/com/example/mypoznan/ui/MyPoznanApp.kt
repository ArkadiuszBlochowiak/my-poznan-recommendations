package com.example.mypoznan.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mypoznan.ui.utils.MyPoznanNavigationType

@Composable
fun MyPoznanApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val viewModel: MyPoznanViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val navigationType: MyPoznanNavigationType = when (windowSize) {
        WindowWidthSizeClass.Compact -> MyPoznanNavigationType.BOTTOM_NAVIGATION
        WindowWidthSizeClass.Medium -> MyPoznanNavigationType.NAVIGATION_RAIL
        WindowWidthSizeClass.Expanded -> MyPoznanNavigationType.NAVIGATION_DRAWER
        else -> MyPoznanNavigationType.BOTTOM_NAVIGATION
    }

    Box(modifier = modifier) {
        if (uiState.isShowingMainPage) {
            RecommendationListScreen(
                uiState = uiState,
                onTabPressed = {
                    viewModel.updateCurrentCategory(it)
                },
                navigationType = navigationType,
                onItemClick = {
                    viewModel.updateCurrentRecommendation(it)
                }
            )
        } else {
            RecommendationDetailsScreen(
                uiState = uiState,
                onBackEvent = {
                    viewModel.resetDetailsView()
                }
            )
        }
    }
}


