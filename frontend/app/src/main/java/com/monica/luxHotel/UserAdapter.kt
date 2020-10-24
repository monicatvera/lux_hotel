package com.monica.luxHotel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monica.luxHotel.models.User

class UserAdapter (var userList: ArrayList<User>, val context: Context) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.user_list_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(userList[position], context)
    }

    override fun getItemCount(): Int {
        return userList.size;
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(b: User, context: Context){
            val txt_user: TextView = itemView.findViewById(R.id.textViewUser)
            //val txt_password : TextView = itemView.findViewById(R.id.textViewPassword)

            txt_user.text = b.user
            //txt_password.text = b.password

            itemView.setOnClickListener {
                val intent = Intent(context, UserDetailActivity::class.java)
                intent.putExtra("userId", b.id)
                intent.putExtra("state", "Showing")
                Log.v("hola caracola antes", b.id.toString())
                context.startActivity(intent)
            }
        }
    }
}