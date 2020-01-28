package com.rinal.kasir.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rinal.kasir.R

@Entity (tableName = "users")
data class Users (
    @PrimaryKey (autoGenerate = true)
    val id : Int? = 0,
    val nameUser : String? = null,
    val password : String? = null,
    val confirmPassword : String? = null,
    val imgUser : Int? = R.drawable.ic_person_black
)