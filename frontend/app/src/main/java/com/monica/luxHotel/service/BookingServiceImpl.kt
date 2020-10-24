package com.monica.luxHotel.service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.monica.luxHotel.models.Booking
import org.json.JSONObject

class BookingServiceImpl : IBookingService {
    override fun getAll(
        context: Context,
        completionHandler: (response: ArrayList<Booking>?) -> Unit
    ) {
        val path = BookingSingleton.getInstance(context).baseUrl + "/api/bookings"
        val arrayRequest = JsonArrayRequest(Request.Method.GET, path, null,
            { response ->
                var bookings: ArrayList<Booking> = ArrayList()
                for (i in 0 until response.length()) {
                    val booking = response.getJSONObject(i)
                    val id = booking.getInt("id")
                    val user = booking.getInt("user")
                    val room = booking.getInt("room")
                    val date = booking.getString("date")
                    val time = booking.getString("time")
                    bookings.add(Booking(id, user, room, date, time))
                }
                completionHandler(bookings)
            },
            { error ->
                completionHandler(ArrayList<Booking>())
            })
        RoomSingleton.getInstance(context).addToRequestQueue(arrayRequest)
    }

    override fun getById(
        context: Context,
        bookingId: Int,
        completionHandler: (response: Booking?) -> Unit
    ) {
        val path = BookingSingleton.getInstance(context).baseUrl + "/api/bookings/" + bookingId
        val objectRequest = JsonObjectRequest(Request.Method.GET, path, null,
            { response ->
                if (response == null) completionHandler(null)

                val id = response.getInt("id")
                val user = response.getInt("user")
                val room = response.getInt("room")
                val date = response.getString("date")
                val time = response.getString("time")

                val booking = Booking(id, user, room, date, time)
                completionHandler(booking)
            },
            { error ->
                completionHandler(null)
            })
        BookingSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun deleteById(context: Context, bookingId: Int, completionHandler: () -> Unit) {
        val path = BookingSingleton.getInstance(context).baseUrl + "/api/bookings/" + bookingId
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

    override fun updateBooking(context: Context, booking: Booking, completionHandler: () -> Unit) {
        val path = BookingSingleton.getInstance(context).baseUrl + "/api/bookings/" + booking.id
        val bookingJson: JSONObject = JSONObject()
        bookingJson.put("id", booking.id.toString())
        bookingJson.put("user", booking.user)
        bookingJson.put("room", booking.room)
        bookingJson.put("date", booking.date)
        bookingJson.put("time", booking.time)
        val objectRequest = JsonObjectRequest(Request.Method.PUT, path, bookingJson,
            { response ->
                completionHandler()
            },
            { error ->
                completionHandler()
            })
        BookingSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun createBooking(context: Context, booking: Booking, completionHandler: () -> Unit) {
        val path = BookingSingleton.getInstance(context).baseUrl + "/api/bookings"
        val bookingJson: JSONObject = JSONObject()
        bookingJson.put("id", booking.id.toString())
        bookingJson.put("user", booking.user)
        bookingJson.put("room", booking.room)
        bookingJson.put("date", booking.date)
        bookingJson.put("time", booking.time)

        val objectRequest = JsonObjectRequest(Request.Method.POST, path, bookingJson,
            { response -> completionHandler() },
            { error -> completionHandler() })
        BookingSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }
}