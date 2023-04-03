package com.example.approomdb.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {

 val readAllData: LiveData<List<User>>
private val rep: UserRep

init {
    val userDao = UserDataBase.getDataBase(application).userDao()
    rep = UserRep(userDao)
    readAllData = rep.readAllData
}
    fun addUse(user: User){
        viewModelScope.launch(Dispatchers.IO){
            rep.addUse(user)
        }
    }
    fun updateUse(user: User){
        viewModelScope.launch(Dispatchers.IO){
            rep.updateUse(user)
        }
    }
    fun deleteUse(user: User){
        viewModelScope.launch(Dispatchers.IO){
            rep.deleteUse(user)
        }
    }
    fun deleteAllUse(){
        viewModelScope.launch(Dispatchers.IO){
            rep.deleteAllUse()
        }
    }



}