package com.consultantree.view_tracker.presentation.library

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.consultantree.view_tracker.presentation.shared.component.CustomImageGrid

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun LibraryScreen(
    viewModel: LibraryViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Library") }
            )
        }
    ) { paddingValues ->
        val pagerState = rememberPagerState(pageCount = { viewModel.categories.value.size })

        LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
            if (!pagerState.isScrollInProgress)
                viewModel.onEvent(LibraryEvent.SelectCategory(pagerState.currentPage))
        }

        LaunchedEffect(key1 = viewModel.selectedCategory.value) {
            pagerState.animateScrollToPage(viewModel.selectedCategory.value)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
        ) {
            ScrollableTabRow(
                selectedTabIndex = viewModel.selectedCategory.value,
                modifier = Modifier.fillMaxWidth(),
                edgePadding = 16.dp,
                divider = { }
            ) {
                viewModel.categories.value.forEachIndexed { index, title ->
                    Tab(
                        selected = viewModel.selectedCategory.value == index,
                        onClick = { viewModel.onEvent(LibraryEvent.SelectCategory(index)) },
                        text = {
                            Text(
                                text = title.name,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }
            }
            HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) { page ->
                CustomImageGrid(
                    images = viewModel.libraryItems.value
                        .filter {
                            it.categoryId == viewModel.categories.value[page].id
                        }
                )
            }
        }
    }
}