package com.example.splitapp.DataLayer.DataViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.splitapp.DataLayer.DataModel.Log
import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.example.splitapp.DataLayer.Repository.UserDataRepository
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val userData:UserDataRepository
):ViewModel() {
    var error = MutableStateFlow<String?>(null)

    suspend fun addSelf(id:String , usermodel: Usermodel){
        try{
            userData.addSelf(id , usermodel)
        } catch (e:Exception){
            android.util.Log.e("Test", "$e")
        }
    }

    suspend fun addGroup(id:String){
        try {
            userData.addGroup(id)
        }catch (e:Exception){

        }
    }
}