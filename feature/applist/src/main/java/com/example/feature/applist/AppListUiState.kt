package com.example.feature.applist

import com.example.domain.entity.App

sealed class AppListUiState {
    object Loading : AppListUiState()
    object Empty : AppListUiState()
    data class Success(val apps: List<App>) : AppListUiState()
    data class Error(val message: String) : AppListUiState()
}