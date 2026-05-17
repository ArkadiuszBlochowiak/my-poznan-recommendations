package com.example.mypoznan.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyPoznanViewModel {
    private val _uiState = MutableStateFlow(MyPoznanUiState())
    val uiState: StateFlow<MyPoznanUiState> = _uiState


}