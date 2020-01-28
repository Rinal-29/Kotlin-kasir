package com.rinal.kasir.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rinal.kasir.db.Databases
import com.rinal.kasir.entity.Users

class MainViewModel (private val databases: Databases) : ViewModel() {

    fun insertUser (users: Users) = databases.userDao().insertUser(users)

    fun updateUser (users: Users) = databases.userDao().updateUser(users)

    fun deleteUser (users: Users) = databases.userDao().deleteUser(users)

    fun getAllUser () : LiveData<List<Users>> = databases.userDao().getAllUser()
}