package com.example.splitapp.DataLayer.DataViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllFriendViewModel @Inject constructor(
    private val database: FirebaseDatabase
):ViewModel() {
    private val _allFriends = MutableStateFlow<List<Usermodel>>(emptyList())
    val allFriends = _allFriends.asStateFlow()


    init {
        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase() {
        // Access Firebase Realtime Database
        val usersRef = database.reference.child("Users")
        viewModelScope.launch {
            usersRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val dataList = mutableListOf<Usermodel>()
                    for (data in snapshot.children) {
                        data.getValue(Usermodel::class.java)?.let {
                            dataList.add(it)
                        }
                    }
                    _allFriends.value = dataList
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle the error
                }
            })
        }
    }

    suspend fun seeAllFriend(){
        allFriends.collect { friends ->
            for (friend in friends) {
                // Do something with friend
                Log.e("name" , "${friend.username}")
            }
        }
    }
}