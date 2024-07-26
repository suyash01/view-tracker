package com.consultantree.view_tracker.domain.model

import java.util.UUID

data class MediaTag(
    val name: String,
    val description: String?,
    val itemId: UUID,
    val tagId: UUID
)
