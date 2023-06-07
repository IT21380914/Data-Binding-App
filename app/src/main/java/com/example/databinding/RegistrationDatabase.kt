package com.example.databinding

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class RegistrationDatabase:RoomDatabase(){
    abstract fun getAllDaos():RegistrationDao

    companion object{
        @Volatile
        private var INSTANCE: RegistrationDatabase? = null
        fun getInstance(context: Context):RegistrationDatabase{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    RegistrationDatabase::class.java,
                    "todo_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}