package com.consultantree.view_tracker.presentation.library

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.consultantree.view_tracker.domain.model.Category
import com.consultantree.view_tracker.domain.model.Media
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor() : ViewModel() {
    private val _categories = mutableStateOf(emptyList<Category>())
    val categories: State<List<Category>> = _categories

    private val _libraryItems = mutableStateOf(emptyList<Media>())
    val libraryItems: State<List<Media>> = _libraryItems

    private val _selectedCategory = mutableIntStateOf(0)
    val selectedCategory: State<Int> = _selectedCategory

    init {
        _categories.value = listOf(Category("Tab 1", 1), Category("Tab 2", 2), Category("Tab 3", 3))
//        _libraryItems.value = listOf(
//            LibraryItem(
//                "Library Item 1",
//                "https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/bx74347-O6KMkECzHPOE.jpg",
//                1,
//                1
//            ),
//            LibraryItem(
//                "Library Item 2",
//                "https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/bx74347-O6KMkECzHPOE.jpg",
//                1,
//                2
//            ),
//            LibraryItem(
//                "Library Item 3",
//                "https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/bx74347-O6KMkECzHPOE.jpg",
//                1,
//                3
//            ),
//            LibraryItem(
//                "Library Item 4",
//                "https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/bx74347-O6KMkECzHPOE.jpg",
//                1,
//                4
//            ),
//            LibraryItem(
//                "Library Item 5",
//                "https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/bx74347-O6KMkECzHPOE.jpg",
//                2,
//                5
//            )
//        )
    }

    fun onEvent(event: LibraryEvent) {
        when (event) {
            is LibraryEvent.SelectCategory -> {
                _selectedCategory.intValue = event.value
            }
        }
    }
}