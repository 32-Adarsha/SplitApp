package com.example.splitapp.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class dataViewModel:ViewModel() {
    var Group =  mutableListOf<Group?>(null)
}