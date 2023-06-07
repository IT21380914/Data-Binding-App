package com.example.databinding

import androidx.room.*

@Dao
interface RegistrationDao {
    @Insert
    suspend fun insertUser(user:User)

    @Delete
    suspend fun deleteUser(user:User)

    @Query("SELECT * From User")
    fun getUser():List<User>

    @Update
    suspend fun updateUser(user:User)
}