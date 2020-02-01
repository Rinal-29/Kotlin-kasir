package com.rinal.kasir.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rinal.kasir.R
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity (tableName = "users")
data class Users (
    @PrimaryKey (autoGenerate = true)
    val id : Int? = 0,
    val email : String? = null,
    val username : String? = null,
    val password : String? = null,
    val fullName : String? = null,
    val outlet : String? = null,
    val imgUser : Int? = R.drawable.ic_photo_camera
) : Parcelable