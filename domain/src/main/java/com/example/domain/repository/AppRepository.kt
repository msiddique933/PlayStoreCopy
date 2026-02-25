package com.example.domain.repository

import com.example.domain.entity.App
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getAllApps(): Flow<List<App>>
    fun searchApps(query: String): Flow<List<App>>
    fun getAppById(id: String): Flow<App?>
    suspend fun addApp(app: App)
    suspend fun toggleInstall(appId: String)
}