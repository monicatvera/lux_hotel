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
import com.monica.luxHotel.models.User
import com.monica.luxHotel.service.UserServiceImpl
import com.squareup.picasso.Picasso

class UserDetailActivity : AppCompatActivity() {
    private lateinit var state: String
    private lateinit var textInputEditTextUser: EditText
    private lateinit var textInputEditTextPassword: EditText
    private lateinit var buttonEdit: Button
    private lateinit var buttonDelete: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        state = this.intent.getStringExtra("state").toString()

        val userId = this.intent.getIntExtra("userId", 1)

        textInputEditTextUser = findViewById(R.id.TextInputEditTextUser)
        textInputEditTextPassword = findViewById(R.id.TextInputEditTextPassword)

        buttonDelete = findViewById(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            deleteUser(userId)
        }

        if (state == "Showing") getUser(userId)

        buttonEdit = findViewById(R.id.buttonEdit)
        buttonEdit.setOnClickListener {
            when (state) {
                "Showing" -> {
                    changeButtonsToEditing()
                }
                "Editing" -> {
                    val user = User(
                        userId,
                        textInputEditTextUser.text.toString(),
                        textInputEditTextPassword.text.toString()
                    )
                    updateUser(user)
                }
                "Adding" -> {
                    val user = User(
                        userId,
                        textInputEditTextUser.text.toString(),
                        textInputEditTextPassword.text.toString()
                    )
                    createUser(user)
                }
            }
        }

        if (state == "Adding") changeButtonsToAdding()
    }

    private fun updateUser(user: User) {
        val userServiceImpl = UserServiceImpl()
        userServiceImpl.updateUser(this, user) { ->
            run {
                changeButtonsToShowing(user.id)
                val intent = Intent(this, UserListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun createUser(user: User) {
        val userServiceImpl = UserServiceImpl()
        userServiceImpl.createUser(this, user) { ->
            run {
                changeButtonsToShowing(user.id)
                val intent = Intent(this, UserListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun changeButtonsToAdding() {
        buttonDelete.visibility = View.GONE
        buttonDelete.isEnabled = false
        buttonEdit.setText("Add User")
        textInputEditTextUser.isEnabled = true
        textInputEditTextPassword.isEnabled = true
        state = "Adding"
    }

    private fun changeButtonsToShowing(roomId: Int) {
        buttonDelete.visibility = View.VISIBLE
        buttonDelete.isEnabled = true
        buttonEdit.setText("Edit Room")
        textInputEditTextUser.isEnabled = false
        textInputEditTextPassword.isEnabled = false
        state = "Showing"
    }

    private fun changeButtonsToEditing() {
        buttonDelete.visibility = View.GONE
        buttonDelete.isEnabled = false
        buttonEdit.setText("Apply changes")
        textInputEditTextUser.isEnabled = true
        textInputEditTextPassword.isEnabled = true
        state = "Editing"
    }

    private fun getUser(userId: Int) {
        val userServiceImpl = UserServiceImpl()
        userServiceImpl.getById(this, userId) { response ->
            run {

                val txt_user: TextInputEditText = findViewById(R.id.TextInputEditTextUser)
                val txt_password: TextInputEditText = findViewById(R.id.TextInputEditTextPassword)

                txt_user.setText(response?.user ?: "")
                txt_password.setText(response?.password ?: "")
            }
        }
    }

    private fun deleteUser(userId: Int) {
        val userServiceImpl = UserServiceImpl()
        userServiceImpl.deleteById(this, userId) { ->
            run {
                val intent = Intent(this, UserListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        menu.findItem(R.id.user)?.isVisible = false
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.room -> {
                val intent = Intent(this, RoomListActivity::class.java)
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