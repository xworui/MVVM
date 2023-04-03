package com.example.approomdb.data

import androidx.lifecycle.LiveData

class UserRep(private var userDao: UserDao){
    val readAllData: LiveData<List<User>> = userDao.readAllData()

  suspend  fun addUse(user: User){
        userDao.addUser(user)

    }
    suspend  fun updateUse(user: User){
        userDao.updateUser(user)
    }
    suspend  fun deleteUse(user: User){
        userDao.deleteUser(user)

    }
    suspend  fun deleteAllUse(){
        userDao.deleteAllUser()

    }

}