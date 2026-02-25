# Play Store Copy — Android Assignment

A simplified Google Play Store clone built with **Jetpack Compose**, **Clean Architecture**, and a **multi-module Gradle** structure.

---

## Module Structure

```
PlayStoreCopy/
├── app/                    Entry point, navigation, Hilt DI wiring
├── domain/                 Pure Kotlin — entities, use cases, repository interfaces
├── data/                   Repository implementations, Room DB, mock seed data
└── feature/
    ├── applist/            App List screen (with search)
    ├── addapp/             Add New App screen (form + validation)
    └── appdetail/          App Detail screen (install / uninstall toggle)
```

### Dependency Graph

```
app  ──────────────────────────────────────┐
      ├── :feature:applist  ──► :domain    │
      ├── :feature:addapp   ──► :domain    ├── :data ──► :domain
      ├── :feature:appdetail──► :domain    │
      └── :data             ──► :domain ───┘
```

**Rules enforced:**
- Feature modules depend **only** on `:domain` (never on `:data`)
- `:data` implements the repository interfaces from `:domain`
- `:app` is the sole wiring point for DI and navigation

---

## Architecture Description

The project follows **Clean Architecture** with three layers:

| Layer        | Module        | Responsibility                                         |
|--------------|---------------|--------------------------------------------------------|
| Presentation | `feature/*`   | Compose UI + `ViewModel` + `StateFlow`                 |
| Domain       | `domain`      | Entities, Use Cases, Repository interfaces (pure Kotlin)|
| Data         | `data`        | Room DB, `AppRepositoryImpl`, Hilt DI modules          |

### Key Patterns
- **Unidirectional Data Flow**: ViewModel exposes `StateFlow<UiState>`; UI observes and emits events upward.
- **Repository Pattern**: `AppRepository` interface defined in `:domain`; implemented in `:data`.
- **In-Memory Install Status**: A `MutableStateFlow<Map<String, Boolean>>` inside `AppRepositoryImpl` tracks installation state. It is **not** persisted — resets on every app restart, as required.
- **Reactive Streams**: `combine()` merges the Room `Flow<List<AppEntity>>` with the in-memory install-status flow so both the list and detail screens update instantly on toggle.

---

## Screens & Features

### App List Screen
- Scrollable list via `LazyColumn`
- Live search with 300 ms debounce (SQL `LIKE` partial match)
- Rounded rectangle app icon with generated color, name, developer, category, rating, install badge
- Loading / Empty / Error states
- FAB navigates to Add App screen

### Add App Screen
- Required fields: **App Name**, **Developer Name**, **Category**
- Optional fields: **Rating** (0–5), **Description**
- Input validation with Snackbar feedback
- On save: random `ColorGenerator` color is generated, app stored in Room, list updates instantly

### App Detail Screen
- Large rounded-rectangle icon (120 dp, 24 dp corner radius)
- Full app info, rating, category
- **Install / Uninstall** button — toggles in-memory state; list badge reflects change immediately

### Search
- Empty query → full list
- Non-empty query → SQL `LIKE %query%` filter via `AppDao`

---

## Technologies & Libraries

| Technology / Library                     | Version   | Purpose                                    |
|------------------------------------------|-----------|--------------------------------------------|
| Kotlin                                   | 2.0.0     | Language                                   |
| Jetpack Compose (BOM)                    | 2024.09   | 100 % declarative UI                       |
| Compose Material 3                       | BOM-managed | UI components & theming                  |
| Navigation Compose                       | 2.7.7     | In-app navigation (string-based routes)    |
| Hilt (Dagger Hilt)                       | 2.51.1    | Dependency injection                       |
| Room                                     | 2.6.1     | Local SQLite persistence for app records   |
| Kotlin Coroutines + Flow                 | 1.8.1     | Async / reactive data streams              |
| ViewModel + StateFlow                    | 2.8.5     | Presentation-layer state management        |
| KSP (Kotlin Symbol Processing)           | 2.0.0-1.0.24 | Code generation for Hilt & Room         |
| Android Gradle Plugin                    | 8.5.2     | Build tooling                              |

---

## Build & Run Instructions

### Prerequisites
| Tool          | Version         |
|---------------|-----------------|
| Android Studio| Hedgehog (2023.1.1) or later |
| JDK           | 17              |
| Android SDK   | API 35 (compileSdk); API 24 (minSdk) |
| Gradle        | 8.9 (via wrapper) |

### Steps

1. **Clone / unzip** the project directory.

2. **Open in Android Studio**
   - `File → Open` → select the `PlayStoreCopy` folder
   - Wait for Gradle sync to complete (it will download dependencies automatically)

3. **Add `local.properties`** if missing (Android Studio usually creates this):
   ```properties
   sdk.dir=/path/to/your/Android/sdk
   ```

4. **Run the app**
   - Select an emulator or connected device (API 24+)
   - Click the **Run ▶** button or press `Shift+F10`

5. **Build from command line** (optional):
   ```bash
   chmod +x gradlew        # macOS / Linux only
   ./gradlew assembleDebug
   ```
   The APK is generated at `app/build/outputs/apk/debug/app-debug.apk`.

> **Note on `gradle-wrapper.jar`:** The binary Gradle wrapper JAR is not included in source control. Android Studio regenerates it automatically on first sync. Alternatively, run `gradle wrapper --gradle-version 8.9` from the project root if you have Gradle installed globally.

---

## Implementation Notes & Potential Improvements

### Notes
- **Install status is intentionally ephemeral.** The `MutableStateFlow<Map<String, Boolean>>` in `AppRepositoryImpl` is held in the Hilt `@Singleton` scope — it lives as long as the OS process. Killing the app resets all statuses to uninstalled, satisfying the requirement.
- **Seed data** is inserted into Room on first launch (when `app_count == 0`) from `MockAppDataSource`. The mock apps cover a variety of categories and colours.
- **ColorGenerator** in `:domain` is pure Kotlin (no Android imports) and picks from 20 hand-curated Material Design tones that are perceptually balanced and easy on the eyes.
- **Search** uses Room's `LIKE %query%` pattern, so it is case-insensitive on ASCII characters without extra configuration.
- The **compose plugin** (`org.jetbrains.kotlin.plugin.compose`) is applied only to modules that contain Compose code (`app`, `feature:applist`, `feature:addapp`, `feature:appdetail`).

### Potential Improvements
| Area                  | Improvement                                                     |
|-----------------------|-----------------------------------------------------------------|
| Persistence           | Persist install status in `DataStore` with a TTL / session flag |
| Paging                | Add `Paging 3` for large app lists                              |
| Image assets          | Real icon images fetched from a URL (Coil / Glide)              |
| Categories            | Dropdown picker instead of free-text for category               |
| Unit tests            | Use Case tests with `Turbine`, ViewModel tests with `TestScope` |
| Integration tests     | Room in-memory DB tests for `AppDao`                            |
| UI tests              | Compose UI tests with `ComposeTestRule`                         |
| Dark mode             | Fully tuned dark colour scheme                                  |
| Animations            | Shared element transitions between list and detail              |
| Offline-first remote  | Replace mock data source with a real REST API + Retrofit         |