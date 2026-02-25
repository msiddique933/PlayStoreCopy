package com.example.domain.util

import kotlin.random.Random

/**
 * Generates pleasant, easy-on-the-eyes ARGB colors.
 * Colors are stored as Int (ARGB bit pattern) and rendered via Compose Color(Int).
 */
object ColorGenerator {

    private val pleasantColors = listOf(
        0xFF4CAF50.toInt(), // Material Green
        0xFF2196F3.toInt(), // Material Blue
        0xFFFF9800.toInt(), // Material Orange
        0xFF9C27B0.toInt(), // Material Purple
        0xFF00BCD4.toInt(), // Material Cyan
        0xFFE91E63.toInt(), // Material Pink
        0xFF3F51B5.toInt(), // Material Indigo
        0xFF009688.toInt(), // Material Teal
        0xFF607D8B.toInt(), // Material Blue Grey
        0xFF795548.toInt(), // Material Brown
        0xFF8BC34A.toInt(), // Material Light Green
        0xFFFF5722.toInt(), // Material Deep Orange
        0xFF673AB7.toInt(), // Material Deep Purple
        0xFF03A9F4.toInt(), // Material Light Blue
        0xFF4DB6AC.toInt(), // Teal 300
        0xFFAED581.toInt(), // Light Green 300
        0xFF7986CB.toInt(), // Indigo 300
        0xFFF06292.toInt(), // Pink 300
        0xFF64B5F6.toInt(), // Blue 300
        0xFFFFB74D.toInt()  // Orange 300
    )

    fun generate(): Int = pleasantColors[Random.nextInt(pleasantColors.size)]
}