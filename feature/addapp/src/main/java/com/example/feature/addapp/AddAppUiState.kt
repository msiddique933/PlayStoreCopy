package com.example.feature.addapp

sealed class AddAppUiState {
    object Idle : AddAppUiState()
    object Loading : AddAppUiState()
    object Success : AddAppUiState()
    data class ValidationError(val message: String) : AddAppUiState()
    data class Error(val message: String) : AddAppUiState()
}