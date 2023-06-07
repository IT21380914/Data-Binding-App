package com.example.databinding

class Repository(private val db: RegistrationDatabase) {

    suspend fun insert(user: User)=db.getAllDaos().insertUser(user)
    suspend fun delete(user: User)=db.getAllDaos().deleteUser(user)
    suspend fun update(user: User)=db.getAllDaos().updateUser(user)
    fun getAllusers()=db.getAllDaos().getUser()

}