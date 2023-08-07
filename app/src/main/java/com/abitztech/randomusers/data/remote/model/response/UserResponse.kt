package com.abitztech.randomusers.data.remote.model.response

data class UserResponse(
    val gender: String,
    val email: String,
    val name: NameResponse,
    val picture: PictureResponse,
    val id: IdResponse,
    val phone: String,
    val cell: String
)
