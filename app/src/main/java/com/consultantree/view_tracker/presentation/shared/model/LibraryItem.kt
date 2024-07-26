package com.consultantree.view_tracker.presentation.shared.model

import com.consultantree.view_tracker.domain.model.Source

data class LibraryItem(
    val source: Source,
    val id: Int,
    val title: String,
    val coverImageUri: String,
    val type: String,
    val format: String,
    val status: String,
    val isAdult: Boolean,
    val chapters: Int?,
    val volumes: Int?,
    val episodes: Int?
)
