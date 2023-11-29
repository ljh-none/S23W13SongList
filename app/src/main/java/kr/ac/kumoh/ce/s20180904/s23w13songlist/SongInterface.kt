package kr.ac.kumoh.ce.s20180904.s23w13songlist

import retrofit2.http.GET

interface SongInterface {
    @GET("song")
    suspend fun getSongs():List<Song>
}