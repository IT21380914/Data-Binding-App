package com.example.databinding

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(val name:String,val email:String,val phone:String,val password:String){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}
