package com.izone.musicplayer.widget

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.izone.musicplayer.R

@Composable
fun MainScreen() {
    val itemList = listOf("IZONE", "OhMyGirl", "BTS")
    var expanded by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("IZONE") }

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.TopCenter) {
        Column {
            Row(
                modifier = Modifier
                    .clickable {
                        expanded = true
                    }
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(end = 15.dp)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "drop down"
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                itemList.forEach { item ->
                    DropDownList(title = item) {
                        title = item
                        expanded = false
                    }
                    Divider()
                }
            }

            Divider()

            MusicItems()
        }
    }
}

@Composable
fun DropDownList(title: String, clickEvent: () -> Unit) {
    DropdownMenuItem(
        onClick = clickEvent) {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 500)
@Composable
fun MainPreview() {
    MainScreen()
}

@Composable
fun MusicItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {

            Image(
                painter = painterResource(id = R.drawable.yena2),
                contentDescription = "album image",
                modifier = Modifier.padding(15.dp))

            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Title")
                Text(text = "Singer")
            }
        }
    }
}

@Composable
fun MusicItems() {
    LazyColumn {
        items(5) {
            MusicItem()
        }
    }
}