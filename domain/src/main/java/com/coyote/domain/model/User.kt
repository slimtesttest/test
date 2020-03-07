package com.coyote.domain.model

data class User(
    val name: UserName,
    val email: String,
    val phone: String,
    val cell: String,
    val nationality: String,
    val gender: String,
    val picture: Picture
)

data class UserName(val title: String, val first: String, val last: String)
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)