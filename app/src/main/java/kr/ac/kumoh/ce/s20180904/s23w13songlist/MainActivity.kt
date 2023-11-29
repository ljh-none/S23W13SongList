package kr.ac.kumoh.ce.s20180904.s23w13songlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
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
fun SongItem(index: Int, song: Song){
    var expanded by remember { mutableStateOf(false) }
    Card(
        elevation = CardDefaults.cardElevation(10.dp)
    ){
        Row(
            modifier = Modifier
                .clickable { expanded = !expanded }
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                //.background(Color(255, 210, 210))
                .padding(8.dp)
        ) {
            AsyncImage(
                model = "https://picsum.photos/300/300?random=$index",
                contentDescription = "노래 앨범 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    //.clip(CircleShape)
                    .clip(RoundedCornerShape(10))
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                //.background(Color(0xffffffcc)),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                TitleText(song.title)
                SingerText(song.singer)
            }
        }
        AnimatedVisibility(expanded) {
            song.lyrics?.let{Text(song.lyrics)}
        }
    }
}

@Composable
fun MyList(list:List<Song>){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ){
        itemsIndexed(list){
                index: Int, item: Song -> SongItem(index, item)
        }
    }
}