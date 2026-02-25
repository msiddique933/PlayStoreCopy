package com.example.feature.appdetail

import com.example.domain.entity.App

sealed class AppDetailUiState {
    object Loading : AppDetailUiState()
    data class Success(val app: App) : AppDetailUiState()
    data class Error(val message: String) : AppDetailUiState()
}