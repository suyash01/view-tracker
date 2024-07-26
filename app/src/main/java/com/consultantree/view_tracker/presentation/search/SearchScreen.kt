package com.consultantree.view_tracker.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.consultantree.view_tracker.presentation.shared.component.CustomImageGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (viewModel.searchActive.value) Modifier else Modifier.padding(
                            horizontal = 16.dp
                        )
                    ),
                query = viewModel.query.value,
                onQueryChange = { viewModel.onEvent(SearchEvent.QueryChange(it)) },
                onSearch = { viewModel.onEvent(SearchEvent.Search) },
                active = viewModel.searchActive.value,
                onActiveChange = { viewModel.onEvent(SearchEvent.SearchState(it)) },
                placeholder = { Text("Search...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "search icon"
                    )
                },
                trailingIcon = {
                    if (viewModel.searchActive.value) {
                        Icon(
                            modifier = Modifier.clickable {
                                if (viewModel.query.value.isEmpty()) {
                                    viewModel.onEvent(SearchEvent.SearchState(false))
                                } else {
                                    viewModel.onEvent(SearchEvent.QueryChange(""))
                                }
                            },
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "close icon"
                        )
                    }
                }
            ) {
                viewModel.history.value.forEach {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Icon(
                            modifier = Modifier.padding(end = 16.dp),
                            imageVector = Icons.Outlined.History,
                            contentDescription = "history icon"
                        )
                        Text(text = it)
                    }
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))

            CustomImageGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                images = viewModel.searchItems.value,
                onClick = {

                }
            )
        }
    }
}