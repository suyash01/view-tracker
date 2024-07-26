package com.consultantree.view_tracker.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.consultantree.view_tracker.presentation.media_detail.ItemDetailScreen
import com.consultantree.view_tracker.presentation.library.LibraryScreen
import com.consultantree.view_tracker.presentation.search.SearchScreen
import com.consultantree.view_tracker.presentation.settings.SettingsScreen

@Composable
fun ViewTracker() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                NavigationBarItem(
                    selected = navBackStackEntry?.destination?.route == "library",
                    onClick = {
                        navController.popBackStack()
                        navController.navigate("library")
                    },
                    icon = {
                        if(navBackStackEntry?.destination?.route == "library") {
                            Icon(
                                imageVector = Icons.Filled.CollectionsBookmark,
                                contentDescription = "library selected"
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.CollectionsBookmark,
                                contentDescription = "library"
                            )
                        }
                    },
                    label = { Text(text = "Library") }
                )
                NavigationBarItem(
                    selected = navBackStackEntry?.destination?.route == "search",
                    onClick = {
                        navController.popBackStack()
                        navController.navigate("search")
                    },
                    icon = {
                        if(navBackStackEntry?.destination?.route == "search") {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "search selected"
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = "search"
                            )
                        }
                    },
                    label = { Text(text = "Search") }
                )
                NavigationBarItem(
                    selected = navBackStackEntry?.destination?.route == "settings",
                    onClick = {
                        navController.popBackStack()
                        navController.navigate("settings")
                    },
                    icon = {
                        if(navBackStackEntry?.destination?.route == "settings") {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "settings selected"
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                contentDescription = "settings"
                            )
                        }
                    },
                    label = { Text(text = "Settings") }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(
                navController = navController,
                startDestination = "library"
            ) {
                composable("library") {
                    LibraryScreen()
                }
                composable("search") {
                    SearchScreen(navController = navController)
                }
                composable("settings") {
                    SettingsScreen()
                }
                composable(
                    route = "itemDetail",
                    arguments = listOf(
                        navArgument(name = "source") {
                            type = NavType.StringType
                        },
                        navArgument(name = "id") {
                            type = NavType.StringType
                        }
                    )
                ) {
                    ItemDetailScreen(navController = navController)
                }
            }
        }
    }
}