package com.monica.luxHotel.service

import android.content.Context
import com.monica.luxHotel.models.User

interface IUserService {
    fun getAll(context: Context, completionHandler: (response: ArrayList<User>?) -> Unit)

    fun getById(context: Context, userId: Int, completionHandler: (response: User?) -> Unit)

    fun deleteById(context: Context, userId: Int, completionHandler: () -> Unit)

    fun updateUser(context: Context, user: User, completionHandler: () -> Unit)

    fun createUser(context: Context, user: User, completionHandler: () -> Unit)
}