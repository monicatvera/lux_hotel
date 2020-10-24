package com.monica.luxHotel.service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.monica.luxHotel.models.Room
import org.json.JSONObject

class RoomServiceImpl : IRoomService {
    override fun getAll(context: Context, completionHandler: (response: ArrayList<Room>?) -> Unit) {
        val path = RoomSingleton.getInstance(context).baseUrl + "/api/rooms"
        val arrayRequest = JsonArrayRequest(Request.Method.GET, path, null,
            { response ->
                var rooms: ArrayList<Room> = ArrayList()
                for (i in 0 until response.length()) {
                    val room = response.getJSONObject(i)
                    val id = room.getInt("id")
                    val name = room.getString("name")
                    val description = room.getString("description")
                    val price = room.getDouble("price")
                    rooms.add(Room(id, name, description, price))
                }
                completionHandler(rooms)
            },
            { error ->
                completionHandler(ArrayList<Room>())
            })
        RoomSingleton.getInstance(context).addToRequestQueue(arrayRequest)
    }

    override fun getById(
        context: Context,
        roomId: Int,
        completionHandler: (response: Room?) -> Unit
    ) {
        val path = RoomSingleton.getInstance(context).baseUrl + "/api/rooms/" + roomId
        val objectRequest = JsonObjectRequest(Request.Method.GET, path, null,
            { response ->
                if (response == null) completionHandler(null)

                val id = response.getInt("id")
                val name = response.getString("name")
                val description = response.getString("description")
                val price = response.getDouble("price")

                val room = Room(id, name, description, price)
                completionHandler(room)
            },
            { error ->
                completionHandler(null)
            })
        RoomSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun deleteById(context: Context, roomId: Int, completionHandler: () -> Unit) {
        val path = RoomSingleton.getInstance(context).baseUrl + "/api/rooms/" + roomId
        val objectRequest = JsonObjectRequest(Request.Method.DELETE, path, null,
            { response ->
                Log.v("Hola caracola", "se borró")
                completionHandler()
            },
            { error ->
                Log.v("Hola caracola", "dió error")
                completionHandler()
            })
        RoomSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun updateRoom(context: Context, room: Room, completionHandler: () -> Unit) {
        val path = RoomSingleton.getInstance(context).baseUrl + "/api/rooms/" + room.id
        val roomJson: JSONObject = JSONObject()
        roomJson.put("id", room.id.toString())
        roomJson.put("name", room.name)
        roomJson.put("description", room.description)
        roomJson.put("price", room.price)

        val objectRequest = JsonObjectRequest(Request.Method.PUT, path, roomJson,
            { response ->
                completionHandler()
            },
            { error ->
                completionHandler()
            })
        RoomSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun createRoom(context: Context, room: Room, completionHandler: () -> Unit) {
        val path = RoomSingleton.getInstance(context).baseUrl + "/api/rooms"
        val roomJson: JSONObject = JSONObject()
        roomJson.put("id", room.id.toString())
        roomJson.put("name", room.name)
        roomJson.put("description", room.description)
        roomJson.put("price", room.price)

        val objectRequest = JsonObjectRequest(Request.Method.POST, path, roomJson,
            { response -> completionHandler() },
            { error -> completionHandler() })
        RoomSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }
}