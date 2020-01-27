package com.rinal.kasir.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rinal.kasir.R

@Entity (tableName = "users")
data class Users (
    @PrimaryKey (autoGenerate = true)
    val id : Int = 0,
    val nameUser : String,
    val password : String,
    val confirmPassword : String,
    val imgUser : Int = R.drawable.ic_person_black
)