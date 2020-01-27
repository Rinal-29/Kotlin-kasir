package com.rinal.kasir.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rinal.kasir.entity.Users

@Database (
    entities = [Users::class], version = 1
)
abstract class Databases : RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object {
        private var INSTANCE : Databases? = null
        private val lock = Any()

        fun getInstance(context: Context): Databases {
            if (INSTANCE == null){
                synchronized(lock){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        Databases::class.java,
                        "kasir.db"
                    ).allowMainThreadQueries().build()
                }
                return INSTANCE as Databases
            }
            return INSTANCE as Databases
        }
    }
}