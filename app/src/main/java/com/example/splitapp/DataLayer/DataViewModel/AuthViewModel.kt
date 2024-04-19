package com.example.splitapp.DataLayer.DataViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitapp.DataLayer.Repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
):ViewModel() {

    val thisUser = authRepository.thisUser

    fun signup(email:String , password:String){
        viewModelScope.launch {
            authRepository.firebaseSignUpWithEmailAndPassword(email , password)
        }
    }
    fun signin(email:String , password:String){
        viewModelScope.launch {
            authRepository.firebaseSignInWithEmailAndPassword(email, password)
        }

    }



}