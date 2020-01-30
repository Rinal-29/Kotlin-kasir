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
    val nameUser : String? = null,
    val password : String? = null,
    val confirmPassword : String? = null,
    val imgUser : Int? = R.drawable.person3,
    val status : String? = "Cashier"
) : Parcelable