package com.consultantree.view_tracker.presentation.search

sealed class SearchEvent {
    data class QueryChange(val value: String): SearchEvent()
    data class SearchState(val value: Boolean): SearchEvent()
    data object Search: SearchEvent()
}