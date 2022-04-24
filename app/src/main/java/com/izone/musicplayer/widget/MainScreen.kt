package com.izone.musicplayer.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val list = listOf("1","2","3")

@Composable
fun MainScreen() {
    var expanded by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("IZONE") }

    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopStart)) {
        Row(
            modifier = Modifier
                .clickable {
                    expanded = true
                }
                .fillMaxWidth()
                .padding(15.dp)
        ){
            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontSize = 11.sp
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "drop down"
                )
        }

//        Button(
//            modifier = Modifier.fillMaxWidth(),
//            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
//            onClick = { expanded = true }
//        ) {
//            Text(
//                text = title,
//                textAlign = TextAlign.Center,
//                fontSize = 11.sp
//            )
//        }
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            DropdownMenuItem(onClick = {
//                title = "IZONE"
//                expanded = false}) {
//                Text("IZONE")
//            }
//            Divider()
//            DropdownMenuItem(onClick = {
//                title = "OhMyGirl"
//                expanded = false
//            }) {
//                Text("OhMyGirl")
//            }
//            Divider()
//            DropdownMenuItem(
//                onClick = {
//                    title = "BTS"
//                    expanded = false
//                }) {
//                Text("BTS")
//            }
//        }
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 500)
@Composable
fun MainPreview() {
    MainScreen()
}