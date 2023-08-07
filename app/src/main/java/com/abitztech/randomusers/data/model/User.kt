package com.abitztech.randomusers.data.model

data class User(
    val id: String,
    val firstName: String,
    val lastName: String?,
    val email: String,
    val phone: String?,
    val cellPhone: String?,
    val largePicture: String?,
    val thumbnailPicture: String?,
)
