package com.izone.musicplayer.widget

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.izone.musicplayer.MPApplication
import com.izone.musicplayer.R
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.repository.MusicRepository
import com.izone.musicplayer.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {

    // dropDownList value
    val itemList = listOf("IZONE", "OhMyGirl", "BTS") // Todo : Scalability
    var title by remember { mutableStateOf("IZONE") }
    var expanded by remember { mutableStateOf(false) }

    // viewmodel state
    val observeList by viewModel.musicList.observeAsState(null)

    Column() {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        ) {

            Row(
                modifier = Modifier
                    .clickable {
                        expanded = true
                    }
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
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

                        Log.d("test", "click $item")

                        title = item
                        expanded = false

                        when (item) {
                            "IZONE" -> {
                                viewModel.requestIzoneRepositories()
                            }
                            "OhMyGirl" -> {
                                viewModel.requestOhmygirlRepositories()
                            }
                            "BTS" -> {
                                viewModel.requestBtsRepositories()
                            }
                        }
                    }
                    Divider()
                }
            }

        }

        MusicItems(observeList)
    }
}

@Composable
fun DropDownList(title: String, clickEvent: () -> Unit) {
    DropdownMenuItem(
        onClick = clickEvent
    ) {
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
    MainScreen(MainViewModel(MusicRepository()))
}

@Composable
fun MusicItem(music: MusicItems) {
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
                modifier = Modifier.padding(15.dp)
            )

            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = music.title)
                Text(text = music.singer)
            }
        }
    }
}

@Composable
fun MusicItems(musicList: List<MusicItems>?) {

    Log.d("musicList", "List is $musicList")

    LazyColumn {
        items(musicList?.size ?: 0) {
            MusicItem(musicList!![it])
        }
    }
}