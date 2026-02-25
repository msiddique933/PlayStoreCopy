package com.example.data.remote

import com.example.data.local.AppEntity
import javax.inject.Inject

/**
 * Provides a curated list of seed apps to populate the database on first launch.
 * Colors are pre-selected Material Design tones that are easy on the eyes.
 */
class MockAppDataSource @Inject constructor() {

    fun getMockApps(): List<AppEntity> = listOf(
        AppEntity(
            id = "mock-001",
            name = "WhatsApp Messenger",
            developerName = "Meta Platforms",
            category = "Social",
            rating = 4.3f,
            description = "Simple, reliable, and private messaging and calling for free, available all over the world.",
            iconColor = 0xFF25D366.toInt()
        ),
        AppEntity(
            id = "mock-002",
            name = "Spotify: Music & Podcasts",
            developerName = "Spotify AB",
            category = "Music & Audio",
            rating = 4.5f,
            description = "With Spotify, you can play millions of songs and podcasts for free. Listen to your favourite artists and albums.",
            iconColor = 0xFF1DB954.toInt()
        ),
        AppEntity(
            id = "mock-003",
            name = "Instagram",
            developerName = "Meta Platforms",
            category = "Social",
            rating = 4.2f,
            description = "Create and share photos, stories and reels. Connect with friends and explore your interests.",
            iconColor = 0xFF9C27B0.toInt()
        ),
        AppEntity(
            id = "mock-004",
            name = "YouTube",
            developerName = "Google LLC",
            category = "Video Players & Editors",
            rating = 4.4f,
            description = "Get the official YouTube app. Watch videos, subscribe to channels and share content.",
            iconColor = 0xFFE53935.toInt()
        ),
        AppEntity(
            id = "mock-005",
            name = "Gmail",
            developerName = "Google LLC",
            category = "Productivity",
            rating = 4.2f,
            description = "Gmail is an easy-to-use email app that saves you time and keeps your messages safe.",
            iconColor = 0xFFEA4335.toInt()
        ),
        AppEntity(
            id = "mock-006",
            name = "Google Maps",
            developerName = "Google LLC",
            category = "Travel & Local",
            rating = 4.6f,
            description = "Navigate, discover and explore with Google Maps. Get real-time GPS navigation and traffic updates.",
            iconColor = 0xFF4CAF50.toInt()
        ),
        AppEntity(
            id = "mock-007",
            name = "Netflix",
            developerName = "Netflix, Inc.",
            category = "Entertainment",
            rating = 4.1f,
            description = "Stream unlimited movies and TV shows on your phone. Watch anywhere, anytime.",
            iconColor = 0xFFE50914.toInt()
        ),
        AppEntity(
            id = "mock-008",
            name = "Uber",
            developerName = "Uber Technologies",
            category = "Travel & Local",
            rating = 4.0f,
            description = "Request a ride from your phone and get picked up in minutes. Uber is available in hundreds of cities worldwide.",
            iconColor = 0xFF1A1A2E.toInt()
        ),
        AppEntity(
            id = "mock-009",
            name = "Discord",
            developerName = "Discord Inc.",
            category = "Communication",
            rating = 4.2f,
            description = "Discord is where you can belong to a school club, gaming group, or just hang out with friends.",
            iconColor = 0xFF5865F2.toInt()
        ),
        AppEntity(
            id = "mock-010",
            name = "Duolingo",
            developerName = "Duolingo",
            category = "Education",
            rating = 4.7f,
            description = "Learn a language for free. Duolingo makes it fun to learn a new language.",
            iconColor = 0xFF58CC02.toInt()
        ),
        AppEntity(
            id = "mock-011",
            name = "Canva: Design, Photo & Video",
            developerName = "Canva",
            category = "Art & Design",
            rating = 4.5f,
            description = "Create stunning designs easily with Canva. Make posters, videos, cards, and more with thousands of templates.",
            iconColor = 0xFF7D2ECA.toInt()
        ),
        AppEntity(
            id = "mock-012",
            name = "Notion - Notes & Docs",
            developerName = "Notion Labs",
            category = "Productivity",
            rating = 4.4f,
            description = "Write, plan and get organized with Notion. All your notes, tasks, wikis, and databases in one place.",
            iconColor = 0xFF2F2F2F.toInt()
        )
    )
}