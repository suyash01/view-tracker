package com.consultantree.view_tracker.data.external.api

import com.consultantree.view_tracker.data.external.client.httpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

class AniList {
    companion object {
        private const val BASE_URL = "https://graphql.anilist.co"

        suspend fun search(query: String, page: Int, perPage: Int): HttpResponse {
            return httpClient.post(BASE_URL) {
                setBody(getSearchQuery(query, page, perPage))
            }
        }

        suspend fun getMedia(id: Int): HttpResponse {
            return httpClient.post(BASE_URL) {
                setBody(getMediaQuery(id))
            }
        }

        private fun getSearchQuery(query: String, page: Int, perPage: Int): String {
            return "{\"query\":\"query Page { Page(page: $page, perPage: $perPage) { media(search: \\\"$query\\\") { id title { english romaji native } coverImage { large } type format status isAdult chapters volumes episodes } } }\"}"
        }

        private fun getMediaQuery(id: Int): String {
            return "{\"query\":\"query Media { Media(id: $id) { id idMal title { romaji english native } type format status description episodes duration chapters volumes coverImage { large } genres tags { name description } isAdult } }\"}"
        }
    }
}