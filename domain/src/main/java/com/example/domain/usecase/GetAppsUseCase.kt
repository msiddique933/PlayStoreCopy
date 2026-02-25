package com.example.domain.usecase

import com.example.domain.entity.App
import com.example.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppsUseCase @Inject constructor(
    private val repository: AppRepository
) {
    operator fun invoke(): Flow<List<App>> = repository.getAllApps()
}