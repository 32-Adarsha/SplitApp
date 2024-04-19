package com.example.splitapp.DataLayer.IRepository


import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface IDisplayRepository {

    suspend fun fetchAllFirend()
}

