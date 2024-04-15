package com.example.splitapp.DataLayer.DataViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.splitapp.DataLayer.DataModel.Log
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.UUID

class DataViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("Groups")
    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    init {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                _data.value = dataSnapshot.value.toString()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error here (e.g., update UI with error message)
            }
        })
    }

    fun onSelect() {
        // You can access the data using data.value or observe it in your UI
        android.util.Log.e("Test", "${data.value}")
    }
}