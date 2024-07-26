package com.consultantree.view_tracker.data.external.model

import com.consultantree.view_tracker.domain.model.Source
import com.consultantree.view_tracker.presentation.shared.model.LibraryItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AniListSearchResponse(
    @SerialName("data")
    val data: Data
) {
    @Serializable
    data class Data(
        @SerialName("Page")
        val page: Page
    ) {
        @Serializable
        data class Page(
            @SerialName("media")
            val media: List<Media>
        ) {
            @Serializable
            data class Media(
                @SerialName("id")
                val id: Int,
                @SerialName("title")
                val title: Title,
                @SerialName("coverImage")
                val coverImage: CoverImage,
                @SerialName("type")
                val type: String,
                @SerialName("format")
                val format: String,
                @SerialName("status")
                val status: String,
                @SerialName("isAdult")
                val isAdult: Boolean,
                @SerialName("chapters")
                val chapters: Int?,
                @SerialName("volumes")
                val volumes: Int?,
                @SerialName("episodes")
                val episodes: Int?
            ) {
                @Serializable
                data class Title(
                    @SerialName("english")
                    val english: String?,
                    @SerialName("romaji")
                    val romaji: String?,
                    @SerialName("native")
                    val native: String?
                )

                @Serializable
                data class CoverImage(
                    @SerialName("large")
                    val large: String
                )
            }
        }
    }

    fun toLibraryItems(): List<LibraryItem> = data.page.media.map {
        LibraryItem(
            source = Source.ANILIST,
            id = it.id,
            title = it.title.english ?: it.title.romaji ?: it.title.native ?: "",
            coverImageUri = it.coverImage.large,
            type = it.type,
            format = it.format,
            status = it.status,
            isAdult = it.isAdult,
            chapters = it.chapters,
            volumes = it.volumes,
            episodes = it.episodes
        )
    }
}
