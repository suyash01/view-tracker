package com.consultantree.view_tracker.presentation.library

sealed class LibraryEvent {
    data class SelectCategory(val value: Int): LibraryEvent()
}