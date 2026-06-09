package com.example.producthub.utils

import androidx.compose.ui.graphics.Color

fun stockStatus(stock: Int): Pair<String, Color> {

    return when {

        stock > 50 ->
            "Available" to Color.Green

        stock in 1..50 ->
            "Limited" to Color(0xFFFF9800)

        else ->
            "Unavailable" to Color.Red
    }
}