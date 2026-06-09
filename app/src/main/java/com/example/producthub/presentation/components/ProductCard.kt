package com.example.producthub.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.producthub.domain.model.Product
import com.example.producthub.utils.stockStatus

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit
) {

    val (status, color) =
        stockStatus(product.stock)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {

        Row(
            modifier = Modifier.padding(12.dp)
        ) {

            AsyncImage(
                model = product.thumbnail,
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {

                Text(product.title)

                Text("₹${product.price}")

                Text("${product.discountPercentage}% OFF")

                Text("⭐ ${product.rating}")

                Text(
                    status,
                    color = color
                )
            }
        }
    }
}
