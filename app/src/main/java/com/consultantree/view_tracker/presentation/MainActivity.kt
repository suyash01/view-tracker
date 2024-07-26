package com.consultantree.view_tracker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.consultantree.view_tracker.presentation.main.ViewTracker
import com.consultantree.view_tracker.ui.theme.ViewTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewTrackerTheme {
                ViewTracker()
            }
        }
    }
}