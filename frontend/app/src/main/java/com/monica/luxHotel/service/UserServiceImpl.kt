package com.monica.luxHotel.service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.monica.luxHotel.models.User
import org.json.JSONObject

class UserServiceImpl : IUserService {
    override fun getAll(context: Context, completionHandler: (response: ArrayList<User>?) -> Unit) {
        val path = UserSingleton.getInstance(context).baseUrl + "/api/users"
        val arrayRequest = JsonArrayRequest(Request.Method.GET, path, null,
            { response ->
                var users: ArrayList<User> = ArrayList()
                for (i in 0 until response.length()) {
                    val user = response.getJSONObject(i)
                    val id = user.getInt("id")
                    val name = user.getString("user")
                    val password = user.getString("password")
                    users.add(User(id, name, password))
                }
                completionHandler(users)
            },
            { error ->
                completionHandler(ArrayList<User>())
            })
        RoomSingleton.getInstance(context).addToRequestQueue(arrayRequest)
    }

    override fun getById(
        context: Context,
        userId: Int,
        completionHandler: (response: User?) -> Unit
    ) {
        val path = UserSingleton.getInstance(context).baseUrl + "/api/users/" + userId
        val objectRequest = JsonObjectRequest(Request.Method.GET, path, null,
            { response ->
                if (response == null) completionHandler(null)

                val id = response.getInt("id")
                val user = response.getString("user")
                val password = response.getString("password")

                val user1 = User(id, user, password)
                completionHandler(user1)
            },
            { error ->
                completionHandler(null)
            })
        RoomSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun deleteById(context: Context, userId: Int, completionHandler: () -> Unit) {
        val path = UserSingleton.getInstance(context).baseUrl + "/api/users/" + userId
        val objectRequest = JsonObjectRequest(Request.Method.DELETE, path, null,
            { response ->
                Log.v("Hola caracola", "se borró")
                completionHandler()
            },
            { error ->
                Log.v("Hola caracola", "dió error")
                completionHandler()
            })
        UserSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun updateUser(context: Context, user: User, completionHandler: () -> Unit) {
        val path = UserSingleton.getInstance(context).baseUrl + "/api/users/" + user.id
        val userJson: JSONObject = JSONObject()
        userJson.put("id", user.id.toString())
        userJson.put("user", user.user)
        userJson.put("password", user.password)

        val objectRequest = JsonObjectRequest(Request.Method.PUT, path, userJson,
            { response ->
                completionHandler()
            },
            { error ->
                completionHandler()
            })
        UserSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun createUser(context: Context, user: User, completionHandler: () -> Unit) {
        val path = UserSingleton.getInstance(context).baseUrl + "/api/users"
        val userJson: JSONObject = JSONObject()
        userJson.put("id", user.id.toString())
        userJson.put("user", user.user)
        userJson.put("password", user.password)

        val objectRequest = JsonObjectRequest(Request.Method.POST, path, userJson,
            { response -> completionHandler() },
            { error -> completionHandler() })
        UserSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }
}