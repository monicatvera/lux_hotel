package com.monica.luxHotel.service

import android.content.Context
import com.monica.luxHotel.models.Booking


interface IBookingService {
    fun getAll(context: Context, completionHandler: (response: ArrayList<Booking>?) -> Unit)

    fun getById(context: Context, bookingId: Int, completionHandler: (response: Booking?) -> Unit)

    fun deleteById(context: Context, bookingId: Int, completionHandler: () -> Unit)

    fun updateBooking(context: Context, booking: Booking, completionHandler: () -> Unit)

    fun createBooking(context: Context, booking: Booking, completionHandler: () -> Unit)
}