package com.example.data.repository

import com.example.data.local.AppDao
import com.example.data.mapper.toDomain
import com.example.data.mapper.toEntity
import com.example.data.remote.MockAppDataSource
import com.example.domain.entity.App
import com.example.domain.repository.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val appDao: AppDao,
    private val mockAppDataSource: MockAppDataSource
) : AppRepository {

    /**
     * In-memory install status map. Intentionally NOT persisted —
     * resets to all-uninstalled on every app restart per requirements.
     */
    private val _installStatus = MutableStateFlow<Map<String, Boolean>>(emptyMap())

    private val repositoryScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        seedIfEmpty()
    }

    private fun seedIfEmpty() {
        repositoryScope.launch {
            if (appDao.getAppCount() == 0) {
                mockAppDataSource.getMockApps().forEach { appDao.insertApp(it) }
            }
        }
    }

    override fun getAllApps(): Flow<List<App>> =
        appDao.getAllApps().combine(_installStatus) { entities, statusMap ->
            entities.map { it.toDomain(statusMap[it.id] ?: false) }
        }

    override fun searchApps(query: String): Flow<List<App>> =
        appDao.searchApps("%$query%").combine(_installStatus) { entities, statusMap ->
            entities.map { it.toDomain(statusMap[it.id] ?: false) }
        }

    override fun getAppById(id: String): Flow<App?> =
        appDao.getAppByIdFlow(id).combine(_installStatus) { entity, statusMap ->
            entity?.toDomain(statusMap[id] ?: false)
        }

    override suspend fun addApp(app: App) {
        appDao.insertApp(app.toEntity())
    }

    override suspend fun toggleInstall(appId: String) {
        val current = _installStatus.value[appId] ?: false
        _installStatus.update { it + (appId to !current) }
    }
}