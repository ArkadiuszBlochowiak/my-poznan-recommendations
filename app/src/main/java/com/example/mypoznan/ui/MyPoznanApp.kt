package com.example.mypoznan.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MyPoznanApp(
    modifier: Modifier = Modifier
) {
    val viewModel: MyPoznanViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    RecommendationListScreen(
        uiState = uiState,
        onTabPressed = {
            viewModel.updateCurrentCategory(it)
        }
    )

    //condition for details view
}


