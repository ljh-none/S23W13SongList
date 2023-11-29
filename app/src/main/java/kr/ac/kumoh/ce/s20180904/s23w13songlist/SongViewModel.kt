package kr.ac.kumoh.ce.s20180904.s23w13songlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class SongViewModel: ViewModel() {
    private val SERVER_URL="https://port-0-s23w10backend-5yc2g32mlomito0q.sel5.cloudtype.app/"
    private val songApi:SongInterface
    private val _songList=MutableLiveData<List<Song>>()
    val songList:LiveData<List<Song>>
        get() = _songList
    init {
        val retrofit= Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build()
        songApi=retrofit.create(SongInterface::class.java)
        fetchSong()
    }
    private fun fetchSong(){
        viewModelScope.launch {
            try {
                val response = songApi.getSongs()
                _songList.value=response
            } catch (e: Exception){
                Log.e("fetchSong()", e.toString())
            }
        }
    }
}