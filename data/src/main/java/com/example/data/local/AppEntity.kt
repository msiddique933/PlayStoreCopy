package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apps")
data class AppEntity(
    @PrimaryKey val id: String,
    val name: String,
    val developerName: String,
    val category: String,
    val rating: Float?,
    val description: String?,
    val iconColor: Int         // ARGB Int; isInstalled is kept in-memory only
)