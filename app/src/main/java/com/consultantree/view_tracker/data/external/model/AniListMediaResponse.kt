package com.consultantree.view_tracker.data.external.model

import com.consultantree.view_tracker.domain.model.Media
import com.consultantree.view_tracker.domain.model.Source
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class AniListMediaResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("idMal")
    val idMal: Int?,
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
    @SerialName("description")
    val description: String?,
    @SerialName("tags")
    val tags: List<Tag>,
    @SerialName("genres")
    val genres: List<String>,
    @SerialName("isAdult")
    val isAdult: Boolean,
    @SerialName("chapters")
    val chapters: Int?,
    @SerialName("volumes")
    val volumes: Int?,
    @SerialName("episodes")
    val episodes: Int?,
    @SerialName("duration")
    val duration: Int?
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

    @Serializable
    data class Tag(
        @SerialName("name")
        val name: String,
        @SerialName("description")
        val description: String
    )

    fun toMedia(): Media = Media(
        source = Source.ANILIST,
        sourceId = id,
        malId = idMal,
        titleEnglish = title.english,
        titleRomaji = title.romaji,
        titleNative = title.native,
        description = description,
        coverImageUri = coverImage.large,
        type = type,
        format = format,
        status = status,
        isAdult = isAdult,
        categoryId = 0,
        chapters = chapters,
        volumes = volumes,
        episodes = episodes,
        duration = duration,
        genres = genres.joinToString(),
        itemId = UUID.randomUUID()
    )
}
