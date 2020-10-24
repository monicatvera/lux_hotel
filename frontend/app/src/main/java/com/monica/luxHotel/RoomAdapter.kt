package com.monica.luxHotel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monica.luxHotel.models.Room
import com.squareup.picasso.Picasso

class RoomAdapter(var roomList: ArrayList<Room>, val context: Context) :
    RecyclerView.Adapter<RoomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.room_list_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(roomList[position], context)
    }

    override fun getItemCount(): Int {
        return roomList.size;
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(b: Room, context: Context) {
            val url = "http://192.168.1.65:8080/img/room-"
            val txt_name: TextView = itemView.findViewById(R.id.textViewName)
            val txt_description: TextView = itemView.findViewById(R.id.textViewDescription)
            val txt_price: TextView = itemView.findViewById(R.id.textViewPrice)
            val img: ImageView = itemView.findViewById(R.id.imageViewRoom)

            txt_name.text = b.name
            txt_description.text = b.description
            txt_price.text = b.price.toString() + " €"

            val imageUrl = url + b.id.toString() + ".jpg"
            Picasso.with(context).load(imageUrl).into(img);

            itemView.setOnClickListener {
                val intent = Intent(context, RoomDetailActivity::class.java)
                intent.putExtra("roomId", b.id)
                intent.putExtra("state", "Showing")
                Log.v("hola caracola antes", b.id.toString())
                context.startActivity(intent)
            }
        }
    }
}