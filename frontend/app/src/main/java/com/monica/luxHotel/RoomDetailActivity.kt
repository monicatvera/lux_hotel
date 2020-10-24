package com.monica.luxHotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.android.material.textfield.TextInputEditText
import com.monica.luxHotel.models.Room
import com.monica.luxHotel.service.RoomServiceImpl
import com.monica.luxHotel.service.RoomSingleton
import com.squareup.picasso.Picasso

class RoomDetailActivity : AppCompatActivity() {
    private lateinit var state: String
    private lateinit var textInputEditTextName: EditText
    private lateinit var textInputEditTextDescription: EditText
    private lateinit var textInputEditTextPrice: EditText
    private lateinit var buttonEdit: Button
    private lateinit var buttonDelete: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        state = this.intent.getStringExtra("state").toString()

        val roomId = this.intent.getIntExtra("roomId", 1)

        textInputEditTextName = findViewById(R.id.TextInputEditTextName)
        textInputEditTextDescription = findViewById(R.id.TextInputEditTextDescription)
        textInputEditTextPrice = findViewById(R.id.TextInputEditTextPrice)

        buttonDelete = findViewById(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            deleteRoom(roomId)
        }

        if (state == "Showing") getRoom(roomId)

        buttonEdit = findViewById(R.id.buttonEdit)
        buttonEdit.setOnClickListener {
            when (state) {
                "Showing" -> {
                    changeButtonsToEditing()
                }
                "Editing" -> {
                    val room = Room(
                        roomId,
                        textInputEditTextName.text.toString(),
                        textInputEditTextDescription.text.toString(),
                        textInputEditTextPrice.text.toString().toDouble()
                    )
                    updateRoom(room)
                }
                "Adding" -> {
                    val room = Room(
                        roomId,
                        textInputEditTextName.text.toString(),
                        textInputEditTextDescription.text.toString(),
                        textInputEditTextPrice.text.toString().toDouble()
                    )
                    createRoom(room)
                }
            }
        }

        if (state == "Adding") changeButtonsToAdding()
    }

    private fun updateRoom(room: Room) {
        val roomServiceImpl = RoomServiceImpl()
        roomServiceImpl.updateRoom(this, room) { ->
            run {
                changeButtonsToShowing(room.id)
                val intent = Intent(this, RoomListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun createRoom(room: Room) {
        val roomServiceImpl = RoomServiceImpl()
        roomServiceImpl.createRoom(this, room) { ->
            run {
                changeButtonsToShowing(room.id)
                val intent = Intent(this, RoomListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun changeButtonsToAdding() {
        buttonDelete.visibility = View.GONE
        buttonDelete.isEnabled = false
        buttonEdit.setText("Add Room")
        textInputEditTextName.isEnabled = true
        textInputEditTextDescription.isEnabled = true
        textInputEditTextPrice.isEnabled = true
        state = "Adding"
    }

    private fun changeButtonsToShowing(roomId: Int) {
        buttonDelete.visibility = View.VISIBLE
        buttonDelete.isEnabled = true
        buttonEdit.setText("Edit Room")
        textInputEditTextName.isEnabled = false
        textInputEditTextDescription.isEnabled = false
        textInputEditTextPrice.isEnabled = false
        state = "Showing"
    }

    private fun changeButtonsToEditing() {
        buttonDelete.visibility = View.GONE
        buttonDelete.isEnabled = false
        buttonEdit.setText("Apply changes")
        textInputEditTextName.isEnabled = true
        textInputEditTextDescription.isEnabled = true
        textInputEditTextPrice.isEnabled = true
        state = "Editing"
    }

    private fun getRoom(roomId: Int) {
        val roomServiceImpl = RoomServiceImpl()
        roomServiceImpl.getById(this, roomId) { response ->
            run {

                val txt_name: TextInputEditText = findViewById(R.id.TextInputEditTextName)
                val txt_description: TextInputEditText =
                    findViewById(R.id.TextInputEditTextDescription)
                val txt_price: TextInputEditText = findViewById(R.id.TextInputEditTextPrice)
                val img: ImageView = findViewById(R.id.imageViewRoomDetail)

                txt_name.setText(response?.name ?: "")
                txt_description.setText(response?.description ?: "")
                txt_price.setText(response?.price.toString() ?: "")

                val url = RoomSingleton.getInstance(this).baseUrl + "/img/room-"
                val imageUrl = url + (response?.id.toString() ?: "0") + ".jpg"
                Picasso.with(this).load(imageUrl).into(img);
            }
        }
    }

    private fun deleteRoom(roomId: Int) {
        val roomServiceImpl = RoomServiceImpl()
        roomServiceImpl.deleteById(this, roomId) { ->
            run {
                val intent = Intent(this, RoomListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        menu.findItem(R.id.room)?.isVisible = false
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.user -> {
                val intent = Intent(this, UserListActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.booking -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}