package com.monica.luxHotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.monica.luxHotel.models.Room
import com.monica.luxHotel.models.User
import com.monica.luxHotel.service.RoomServiceImpl
import com.monica.luxHotel.service.UserServiceImpl

class UserListActivity : AppCompatActivity() {
    private lateinit var users: ArrayList<User>
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: UserAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        users = ArrayList<User>()

        viewManager = LinearLayoutManager(this)

        viewAdapter = UserAdapter(users, this)


        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewUser)
        // use a linear layout manager
        recyclerView.layoutManager = viewManager

        // specify an viewAdapter (see also next example)
        recyclerView.adapter = viewAdapter

        getAllUsers()

        val fab: FloatingActionButton = findViewById(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this, UserDetailActivity::class.java)
            intent.putExtra("state", "Adding")
            startActivity(intent)
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

    private fun getAllUsers() {

        val userServiceImpl = UserServiceImpl()
        userServiceImpl.getAll(this) { response ->
            run {
                if (response != null) {
                    viewAdapter.userList = response
                }
                viewAdapter.notifyDataSetChanged()
            }
        }
    }

}