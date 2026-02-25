package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM apps ORDER BY name ASC")
    fun getAllApps(): Flow<List<AppEntity>>

    @Query("SELECT * FROM apps WHERE name LIKE :query ORDER BY name ASC")
    fun searchApps(query: String): Flow<List<AppEntity>>

    @Query("SELECT * FROM apps WHERE id = :id")
    fun getAppByIdFlow(id: String): Flow<AppEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApp(app: AppEntity)

    @Query("SELECT COUNT(*) FROM apps")
    suspend fun getAppCount(): Int
}