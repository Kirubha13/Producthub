package com.example.producthub.presentation.navigation

sealed class Screen(
    val route: String
) {

    data object Splash :
        Screen("splash")

    data object Login :
        Screen("login")

    data object Home :
        Screen("home")

    data object Detail :
        Screen("detail/{id}") {

        fun create(id: Int): String {
            return "detail/$id"
        }
    }
}