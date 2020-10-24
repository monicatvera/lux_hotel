package com.monica.luxHotel.service

import android.content.Context
import com.monica.luxHotel.models.Room

interface IRoomService {
    fun getAll(context: Context, completionHandler: (response: ArrayList<Room>?) -> Unit)

    fun getById(context: Context, roomId: Int, completionHandler: (response: Room?) -> Unit)

    fun deleteById(context: Context, roomId: Int, completionHandler: () -> Unit)

    fun updateRoom(context: Context, room: Room, completionHandler: () -> Unit)

    fun createRoom(context: Context, room: Room, completionHandler: () -> Unit)
}