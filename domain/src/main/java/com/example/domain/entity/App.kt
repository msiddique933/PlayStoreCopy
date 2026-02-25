package com.example.domain.entity

import java.util.UUID

data class App(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val developerName: String,
    val category: String,
    val rating: Float? = null,
    val description: String? = null,
    val iconColor: Int,          // ARGB Int (e.g. 0xFF4CAF50.toInt())
    val isInstalled: Boolean = false
)