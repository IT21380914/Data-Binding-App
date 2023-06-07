package com.example.databinding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserAdapter:RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    lateinit var data:List<User>
    lateinit var context: Context
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvUserName:TextView
        val ivUpdate:ImageView
        val ivDelete:ImageView

        init {
            tvUserName=view.findViewById(R.id.tvUserName)
            ivUpdate=view.findViewById(R.id.ivUpdate)
            ivDelete=view.findViewById(R.id.ivDelete)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_users,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val repository=Repository(RegistrationDatabase.getInstance(context))
        holder.tvUserName.text="${data[position].id} "+"${data[position].name} "
        holder.ivDelete.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                repository.delete(data[position])
                val data=repository.getAllusers()
                withContext(Dispatchers.Main){
                    setData(data, context)
                }}

        }
    }
    fun setData(data:List<User>,context:Context){
        this.data=data
        this.context=context
        notifyDataSetChanged()
    }
}