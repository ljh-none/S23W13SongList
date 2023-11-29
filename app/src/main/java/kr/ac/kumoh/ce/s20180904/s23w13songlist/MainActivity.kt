package kr.ac.kumoh.ce.s20180904.s23w13songlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ac.kumoh.ce.s20180904.s23w13songlist.ui.theme.S23W13SongListTheme

class MainActivity : ComponentActivity() {
    private val model:SongViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(model)
        }
    }
}



@Composable
fun MainScreen(model: SongViewModel){
    val list by model.songList.observeAsState(emptyList())
    S23W13SongListTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyList(list)
        }
    }
}

@Composable
fun TitleText(title: String){
    Text(title, fontSize = 30.sp)
}

@Composable
fun SingerText(singer: String){
    Text(singer, fontSize = 20.sp)
}

@Composable
fun SongItem(song: Song){
    Column(
        modifier = Modifier.
        fillMaxWidth().
        background(Color(0xffffffcc)).
        padding(16.dp)
    ){
        TitleText(song.title)
        SingerText(song.singer)
    }
}

@Composable
fun MyList(list:List<Song>){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ){
        items(list){SongItem(it)}
    }
}