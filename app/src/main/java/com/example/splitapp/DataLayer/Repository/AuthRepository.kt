package com.example.splitapp.DataLayer.Repository

import android.nfc.Tag
import android.util.Log
import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.example.splitapp.DataLayer.IRepository.IAuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class AuthRepository @Inject constructor(
    private val auth:FirebaseAuth,
    private val database:FirebaseDatabase
) : IAuthRepository {
    private val _thisUser = MutableStateFlow<FirebaseUser?>(null)
    val thisUser = _thisUser.asStateFlow()
    override fun firebaseSignUpWithEmailAndPassword(
        email: String,
        password: String
    ) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(){ task ->
                    if (task.isSuccessful) {
                        Log.e("Error", "UserCreatedSuccessfully")
                        _thisUser.value = auth.currentUser
                        val newEmail = email.replace("." , "")
                        database.reference.child("AllUser").child("$newEmail").setValue(thisUser.value?.uid)
                    } else{
                        Log.e("Error", "${task.exception}")
                    }
                }

    }

    override fun firebaseSignInWithEmailAndPassword(
        email: String, password: String
    ) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(){ task ->
                if (task.isSuccessful) {
                    Log.e("Error", "UserCreatedSuccessfully")
                    _thisUser.value = auth.currentUser
                } else{
                    Log.e("Error", "${task.exception}")
                }
            }
    }
}