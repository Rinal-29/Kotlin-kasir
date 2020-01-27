package com.rinal.kasir.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.rinal.kasir.entity.Users

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUser() : LiveData<List<Users>>

    @Query("SELECT * FROM users WHERE nameUser LIKE :nameUser LIMIT 1")
    fun getUsername(nameUser: String)

    @Query("SELECT * FROM users WHERE password LIKE :password LIMIT 1")
    fun getPassword(password: String)

    @Query("SELECT * FROM users WHERE confirmPassword LIKE :confirmPassword LIMIT 1")
    fun getConfirmPassword(confirmPassword: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser()

    @Update
    fun updateUser(users: Users)

    @Delete
    fun deleteUser(users: Users)
}