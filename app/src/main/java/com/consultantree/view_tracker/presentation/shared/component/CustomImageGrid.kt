package com.consultantree.view_tracker.presentation.shared.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.consultantree.view_tracker.presentation.shared.model.LibraryItem

@Composable
fun CustomImageGrid(
    modifier: Modifier = Modifier,
    images: List<LibraryItem>,
    onClick: (item: LibraryItem) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(images) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f / 1.6f)
                    .clip(RoundedCornerShape(4.dp))
                    .clickable(onClick = { onClick(it) }),
                model = it.coverImageUri,
                contentDescription = it.title,
                contentScale = ContentScale.Crop
            )
        }
    }
}