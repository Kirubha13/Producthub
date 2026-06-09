package com.example.producthub.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.producthub.domain.model.Product

@Composable
fun ProductDetailContent(
    product: Product
) {

    val pagerState = rememberPagerState(
        pageCount = { product.images.size }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.height(300.dp)
        ) { page ->

            AsyncImage(
                model = product.images[page],
                contentDescription = product.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = product.title,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = product.description,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "₹${product.price}",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Category: ${product.category}",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = "Brand: ${product.brand}",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = "Stock: ${product.stock}",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}