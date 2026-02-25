package com.example.feature.addapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.App
import com.example.domain.usecase.AddAppUseCase
import com.example.domain.util.ColorGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAppViewModel @Inject constructor(
    private val addAppUseCase: AddAppUseCase
) : ViewModel() {

    var name by mutableStateOf("")
        private set
    var developerName by mutableStateOf("")
        private set
    var category by mutableStateOf("")
        private set
    var rating by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow<AddAppUiState>(AddAppUiState.Idle)
    val uiState: StateFlow<AddAppUiState> = _uiState.asStateFlow()

    fun onNameChanged(value: String) { name = value }
    fun onDeveloperNameChanged(value: String) { developerName = value }
    fun onCategoryChanged(value: String) { category = value }
    fun onRatingChanged(value: String) {
        // Allow only valid float input
        if (value.isEmpty() || value.matches(Regex("^\\d{0,1}(\\.\\d{0,1})?\$"))) {
            rating = value
        }
    }
    fun onDescriptionChanged(value: String) { description = value }

    fun saveApp() {
        if (name.isBlank()) {
            _uiState.value = AddAppUiState.ValidationError("App name is required")
            return
        }
        if (developerName.isBlank()) {
            _uiState.value = AddAppUiState.ValidationError("Developer name is required")
            return
        }
        if (category.isBlank()) {
            _uiState.value = AddAppUiState.ValidationError("Category is required")
            return
        }

        val parsedRating = rating.toFloatOrNull()
        if (rating.isNotBlank() && (parsedRating == null || parsedRating < 0f || parsedRating > 5f)) {
            _uiState.value = AddAppUiState.ValidationError("Rating must be between 0 and 5")
            return
        }

        viewModelScope.launch {
            _uiState.value = AddAppUiState.Loading
            try {
                val app = App(
                    name = name.trim(),
                    developerName = developerName.trim(),
                    category = category.trim(),
                    rating = parsedRating,
                    description = description.trim().ifBlank { null },
                    iconColor = ColorGenerator.generate()
                )
                addAppUseCase(app)
                _uiState.value = AddAppUiState.Success
            } catch (e: Exception) {
                _uiState.value = AddAppUiState.Error(e.message ?: "Failed to save app")
            }
        }
    }

    fun resetState() {
        _uiState.value = AddAppUiState.Idle
    }
}