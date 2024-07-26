package com.consultantree.view_tracker.presentation.media_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.consultantree.view_tracker.domain.model.Media
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MediaDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _item = mutableStateOf(null)
    val item: State<Media?> = _item

    init {
        savedStateHandle.get<String>("source")
    }
}