package com.example.approomdb.data

import androidx.lifecycle.LiveData

class UserRep(private var userDao: UserDao){
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    fun addUse(user: User){
        userDao.addUser(user)
    }
}