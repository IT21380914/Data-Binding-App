package com.example.databinding

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.databinding.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        val edtName=binding.edtName
        val edtEmail=binding.edtEmail
        val edtPhone=binding.edtPhone
        val edtPassword=binding.edtPassword
        val edtRePassword=binding.edtRePassword
        val btnSubmit=binding.btnSubmit
        val btnCancel=binding.btnCancel
        val repository=Repository(RegistrationDatabase.getInstance(this))

        btnSubmit.setOnClickListener{
            showAlertBox(
                this,
                edtName.text.toString(),
                edtEmail.text.toString(),
                edtPhone.text.toString(),
                edtPassword.text.toString(),
                edtRePassword.text.toString()
            )




        }

        btnCancel.setOnClickListener{
            edtName.setText("")
            edtEmail.setText("")
            edtPhone.setText("")
            edtPassword.setText("")
            edtRePassword.setText("")
        }


    }

    fun showAlertBox(
        context:Context,
        name:String,
        email:String,
        phone:String,
        password:String,
        rePassword:String
    ){
        if(name.isEmpty()){
            Toast.makeText(context,"Please enter name",Toast.LENGTH_LONG).show()
        }else if(email.isEmpty())
        {
            Toast.makeText(context,"Please enter email",Toast.LENGTH_LONG).show()
        }else if(phone.isEmpty()){
            Toast.makeText(context,"Please enter mobile number",Toast.LENGTH_LONG).show()
        } else if(password.length<10){
            Toast.makeText(context,"Password should be 10 digits long",Toast.LENGTH_LONG).show()
        }
        else if(password!=rePassword){
            Toast.makeText(context,"Passwords do not match",Toast.LENGTH_LONG).show()
        }else{
            val user=User(name,email,phone, password)
            val repository=Repository(RegistrationDatabase.getInstance(this))
            val builder = AlertDialog.Builder(context)
            val message = "Name :$name\n" + "Email :$email\n" + "Phone number : $phone"

            builder.setTitle("Confirm Details")
            builder.setMessage(message)

            builder.setPositiveButton("Ok") { _, _ ->
                Toast.makeText(context, "Submitted", Toast.LENGTH_LONG).show()
                CoroutineScope(Dispatchers.IO).launch {
                    repository.insert(user)
                }
                val intent= Intent(this,ViewUsers::class.java)
                startActivity(intent)
                finish()
            }
            builder.setNegativeButton("Cancel") { _, _ -> }

            val dialog=builder.create()
            dialog.show()


        }
    }
}