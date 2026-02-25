package com.example.data.mapper

import com.example.data.local.AppEntity
import com.example.domain.entity.App

fun AppEntity.toDomain(isInstalled: Boolean = false): App = App(
    id = id,
    name = name,
    developerName = developerName,
    category = category,
    rating = rating,
    description = description,
    iconColor = iconColor,
    isInstalled = isInstalled
)

fun App.toEntity(): AppEntity = AppEntity(
    id = id,
    name = name,
    developerName = developerName,
    category = category,
    rating = rating,
    description = description,
    iconColor = iconColor
)