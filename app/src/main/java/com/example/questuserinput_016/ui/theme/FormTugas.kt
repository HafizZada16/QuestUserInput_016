package com.example.questuserinput_016.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Forminput() {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFE0F7FA), Color(0xFFA5D6A7), Color(0xFFC8E6C9))
    )
}