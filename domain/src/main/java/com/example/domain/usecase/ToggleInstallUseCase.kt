package com.example.domain.usecase

import com.example.domain.repository.AppRepository
import javax.inject.Inject

class ToggleInstallUseCase @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke(appId: String) = repository.toggleInstall(appId)
}