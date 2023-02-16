package com.example.recyclerview2

import android.app.Application
import com.example.recyclerview2.service.UserService

class App:Application(){
    val userService=UserService()
}