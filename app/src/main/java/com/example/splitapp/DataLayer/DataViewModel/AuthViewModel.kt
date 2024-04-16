package com.example.splitapp.DataLayer.DataViewModel

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitapp.DataLayer.Repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
):ViewModel() {

    fun signup(email:String , password:String):Deferred<FirebaseUser?>{
        return viewModelScope.async {
            return@async authRepository.firebaseSignUpWithEmailAndPassword(email, password)
        }
    }
    fun signin(email:String , password:String):Deferred<FirebaseUser?>{
        return viewModelScope.async {
            return@async authRepository.firebaseSignInWithEmailAndPassword(email, password)
        }

    }



}