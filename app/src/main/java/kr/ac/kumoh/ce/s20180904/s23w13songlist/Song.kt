package kr.ac.kumoh.ce.s20180904.s23w13songlist

data class Song(
    var id:Int,
    var title: String,
    var singer: String,
    var rating: Int,
    val lyrics: String?,
)