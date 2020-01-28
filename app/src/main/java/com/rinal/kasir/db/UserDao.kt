package com.rinal.kasir.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.rinal.kasir.entity.Users

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUser() : LiveData<List<Users>>

    @Query("SELECT * FROM users WHERE nameUser = :username")
    fun getByName(username: String?)  : LiveData<List<Users>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: Users)

    @Update
    fun updateUser(users: Users)

    @Delete
    fun deleteUser(users: Users)
}