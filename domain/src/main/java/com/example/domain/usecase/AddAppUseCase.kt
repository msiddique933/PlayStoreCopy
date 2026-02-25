package com.example.domain.usecase

import com.example.domain.entity.App
import com.example.domain.repository.AppRepository
import javax.inject.Inject

class AddAppUseCase @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke(app: App) = repository.addApp(app)
}