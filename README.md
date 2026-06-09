Producthub
A modern Android application built with Jetpack Compose, Clean Architecture, MVVM, Hilt, Paging 3, Room, and Retrofit. The application allows users to log in, browse products from a remote API, view product details, filter products by category, and persist session information across app launches.

Features
Authentication
Email and password login screen Form validation: Valid email format Non-empty password Minimum password length of 8 characters User session persistence using DataStore Auto-login if session exists Logout functionality Logged-in user email displayed throughout the app

Product Listing
Fetches products from remote API Infinite scrolling using Paging 3 Local caching using Room Database RemoteMediator implementation for offline-first architecture Dynamic category filtering Product card displaying: Thumbnail Title Price Discount Percentage Rating Stock Status

Product Details
Product image carousel Product information: Title Description Price Category Stock User email displayed Back navigation support

Architecture
The application follows Clean Architecture principles with clear separation of concerns.

Presentation │ ├── UI (Compose Screens) ├── ViewModels ├── UiState │ Domain │ ├── Models ├── Repository Contracts ├── UseCases │ Data │ ├── Remote (Retrofit) ├── Local (Room) ├── Repository Implementations ├── Paging RemoteMediator └── DataStore

Architecture Layers
Presentation Layer
Responsible for UI and user interaction.

Components:
Compose Screens ViewModels UiState classes Navigation

Examples:
LoginScreen HomeScreen ProductDetailScreen SplashScreen Domain Layer

Contains business logic.

Components:
Domain Models Repository Interfaces Use Cases

Use Cases:
GetProductsUseCase GetProductDetailUseCase LoginUseCase LogoutUseCase GetUserEmailUseCase

Benefits:
Reusable business logic Independent of Android framework Easier testing Data Layer

Responsible for data retrieval and storage.

Components:
Remote Retrofit API Service Local Room Database DAO Entities Session DataStore Repository Implementations

Implements repository contracts defined in Domain Layer.
