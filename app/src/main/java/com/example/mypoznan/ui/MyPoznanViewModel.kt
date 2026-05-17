package com.example.mypoznan.ui

import androidx.lifecycle.ViewModel
import com.example.mypoznan.model.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MyPoznanViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MyPoznanUiState())
    val uiState: StateFlow<MyPoznanUiState> = _uiState

    fun updateCurrentCategory(category: Category) {
        _uiState.update { currentState ->
            currentState.copy(
                currentCategory = category
            )
        }
    }
}