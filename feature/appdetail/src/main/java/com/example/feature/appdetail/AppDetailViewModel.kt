package com.example.feature.appdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetAppByIdUseCase
import com.example.domain.usecase.ToggleInstallUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getAppByIdUseCase: GetAppByIdUseCase,
    private val toggleInstallUseCase: ToggleInstallUseCase
) : ViewModel() {

    private val appId: String = checkNotNull(savedStateHandle["appId"])

    val uiState: StateFlow<AppDetailUiState> = getAppByIdUseCase(appId)
        .map { app ->
            if (app != null) AppDetailUiState.Success(app)
            else AppDetailUiState.Error("App not found")
        }
        .catch { e -> emit(AppDetailUiState.Error(e.message ?: "Something went wrong")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = AppDetailUiState.Loading
        )

    fun toggleInstall() {
        viewModelScope.launch {
            toggleInstallUseCase(appId)
        }
    }
}