package com.consultantree.view_tracker.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.consultantree.view_tracker.data.external.api.AniList
import com.consultantree.view_tracker.data.external.model.AniListSearchResponse
import com.consultantree.view_tracker.presentation.shared.model.LibraryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.call.body
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    private var searchjob: Job? = null

    private val _query = mutableStateOf("")
    val query: State<String> = _query

    private val _searchActive = mutableStateOf(false)
    val searchActive: State<Boolean> = _searchActive

    private val _history = mutableStateOf(emptyList<String>())
    val history: State<List<String>> = _history

    private val _searchItems = mutableStateOf(emptyList<LibraryItem>())
    val searchItems: State<List<LibraryItem>> = _searchItems

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.QueryChange -> {
                _query.value = event.value
            }

            is SearchEvent.SearchState -> {
                _searchActive.value = event.value
            }

            is SearchEvent.Search -> {
                _searchActive.value = false
                _history.value += listOf(_query.value)
                doSearch(_query.value)
            }
        }
    }

    private fun doSearch(query: String) {
        searchjob?.cancel()
        searchjob = viewModelScope.launch {
            _searchItems.value = AniList.search(query, 1, 48).body<AniListSearchResponse>().toLibraryItems()
        }
    }
}