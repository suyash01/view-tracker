package com.consultantree.view_tracker.presentation.media_detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailScreen(
    navController: NavController
) {

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Item Detail") }
            )
        }
    ) {

    }
}