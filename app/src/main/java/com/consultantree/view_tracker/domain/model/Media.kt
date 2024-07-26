package com.consultantree.view_tracker.domain.model

import java.util.UUID

data class Media(
    val source: Source,
    val sourceId: Int,
    val malId: Int?,
    val titleEnglish: String?,
    val titleRomaji: String?,
    val titleNative: String?,
    val description: String?,
    val coverImageUri: String,
    val type: String,
    val format: String?,
    val status: String,
    val isAdult: Boolean,
    val categoryId: Int,
    val chapters: Int?,
    val volumes: Int?,
    val episodes: Int?,
    val duration: Int?,
    val genres: String,
    val itemId: UUID
)
