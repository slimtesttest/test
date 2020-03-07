package com.coyote.data.db.tables

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coyote.domain.model.Picture
import com.coyote.domain.model.UserName

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @Embedded
    val name: UserName,
    val email: String,
    val phone: String,
    val cell: String,
    val nationality: String,
    val gender: String,
    @Embedded
    val picture: Picture
)
