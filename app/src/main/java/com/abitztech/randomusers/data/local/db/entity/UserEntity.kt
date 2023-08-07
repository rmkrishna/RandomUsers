package com.abitztech.randomusers.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abitztech.randomusers.data.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo val firstName: String,
    @ColumnInfo val lastName: String?,
    @ColumnInfo val email: String,
    @ColumnInfo val phone: String?,
    @ColumnInfo val cellPhone: String?,
    @ColumnInfo val largePicture: String?,
    @ColumnInfo val thumbnailPicture: String?,
)

fun UserEntity.getModel(): User {
    return User(id, firstName, lastName, email, phone, cellPhone, largePicture, thumbnailPicture)
}

fun List<UserEntity>.getModels() = map { it.getModel() }