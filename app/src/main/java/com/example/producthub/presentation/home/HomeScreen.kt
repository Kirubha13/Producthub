package com.example.producthub.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.producthub.presentation.components.ProductCard
import com.example.producthub.presentation.components.ProductShimmer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    vm: HomeViewModel = hiltViewModel(),
    navigateDetail: (Int) -> Unit,
    navigateLogin: () -> Unit
) {

    val products =
        vm.products.collectAsLazyPagingItems()
    val userEmail by vm.userEmail.collectAsState()
    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Column {

                        Text(
                            text = "ProductHub"
                        )

                        Text(
                            text = userEmail,
                            style = MaterialTheme
                                .typography
                                .bodySmall
                        )
                    }
                },

                actions = {
                    Text("Log Out", color = Color.Red, modifier = Modifier.clickable{
                        vm.logout {
                            navigateLogin()
                        }
                    })
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {

            items(products.itemCount) { index ->

                products[index]?.let {

                    ProductCard(
                        product = it
                    ) {
                        navigateDetail(it.id)
                    }
                }
            }

            if (products.loadState.refresh is LoadState.Loading) {

                item {
                    ProductShimmer()
                }
            }
        }
    }
}