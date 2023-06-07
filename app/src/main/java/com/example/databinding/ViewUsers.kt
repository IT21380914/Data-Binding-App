package com.example.databinding

import android.content.Intent
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databinding.databinding.ActivityViewUsersBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewUsers : AppCompatActivity() {

    private lateinit var binding: ActivityViewUsersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_users)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_view_users)
        val repository=Repository(RegistrationDatabase.getInstance(this))
        val btnBack=binding.btnBack
        val rvUsers=binding.rvUsers
        val ui=this

        val recyclerView:RecyclerView=findViewById(R.id.rvUsers)
        val adapter=UserAdapter()
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            val data=repository.getAllusers()
            adapter.setData(data,ui)
        }

        btnBack.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}