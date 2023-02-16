package com.example.recyclerview2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview2.databinding.ActivityMainBinding
import com.example.recyclerview2.model.User
import com.example.recyclerview2.service.UserService
import com.example.recyclerview2.service.UsersListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter:UserAdapter

    private val userService: UserService
        get()=(applicationContext as App).userService

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter= UserAdapter(object : UserActionListener{
            override fun onUserMove(user: User, moveBy: Int) {
                userService.moveUser(user,moveBy)
            }

            override fun onUserDelete(user: User) {
                userService.deleteUser(user)
            }

            override fun onUserDetails(user: User) {
                Toast.makeText(this@MainActivity,"User: ${user.name}",Toast.LENGTH_SHORT).show()
            }

        })

        val layoutManager=LinearLayoutManager(this)
        binding.recyclerView.layoutManager=layoutManager
        binding.recyclerView.adapter=adapter

        userService.addListener(usersListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        userService.deleteListener(usersListener)
    }
    private val usersListener:UsersListener={
        adapter.users=it
    }
}