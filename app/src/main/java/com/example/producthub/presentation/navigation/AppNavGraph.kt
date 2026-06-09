package com.example.producthub.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.producthub.presentation.detail.ProductDetailScreen
import com.example.producthub.presentation.home.HomeScreen
import com.example.producthub.presentation.login.LoginScreen
import com.example.producthub.presentation.splash.SplashScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {

            SplashScreen(

                navigateToHome = {

                    navController.navigate(
                        Screen.Home.route
                    ) {

                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                },

                navigateToLogin = {

                    navController.navigate(
                        Screen.Login.route
                    ) {

                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Screen.Login.route) {

            LoginScreen(

                navigateHome = {

                    navController.navigate(
                        Screen.Home.route
                    ) {

                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Screen.Home.route) {

            HomeScreen(

                navigateDetail = { id ->

                    navController.navigate(
                        Screen.Detail.create(id)
                    )
                },

                navigateLogin = {

                    navController.navigate(
                        Screen.Login.route
                    ) {

                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {

            ProductDetailScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}