package com.izone.musicplayer.widget

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen() {
    Text(
        text = "Hello"
    )
}

@Preview(showBackground = true, widthDp = 300, heightDp = 500)
@Composable
fun MainPreview() {
    MainScreen()
}