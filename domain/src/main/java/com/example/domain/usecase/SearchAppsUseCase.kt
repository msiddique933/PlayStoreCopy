package com.example.domain.usecase

import com.example.domain.entity.App
import com.example.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchAppsUseCase @Inject constructor(
    private val repository: AppRepository
) {
    operator fun invoke(query: String): Flow<List<App>> = repository.searchApps(query)
}