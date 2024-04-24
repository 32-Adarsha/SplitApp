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
    private val _thisAttempS = MutableStateFlow<Int>(0)
    var attempt  = _thisAttempS.asStateFlow()
    private val _thisAttemp = MutableStateFlow<Int>(0)
    var attempt2  = _thisAttemp.asStateFlow()
    private val _loading = MutableStateFlow<Boolean>(false)
    var loading  = _loading.asStateFlow()
    private var _error = MutableStateFlow<String?>(null)
    var error = _error.asStateFlow()
    private val _loading2 = MutableStateFlow<Boolean>(false)
    var loading2  = _loading2.asStateFlow()
    private var _error2 = MutableStateFlow<String?>(null)
    var error2 = _error2.asStateFlow()

    override fun firebaseSignUpWithEmailAndPassword(
        email: String,
        password: String
    ) {
        _loading2.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(){ task ->
                    if (task.isSuccessful) {
                        _thisUser.value = auth.currentUser
                        val newEmail = email.replace("." , "")
                        database.reference.child("AllUser").child("$newEmail").setValue(thisUser.value?.uid)
                    } else{
                        _error2.value = task.exception?.message.toString()
                        _loading2.value = false
                    }
                }

    }

    override fun firebaseSignInWithEmailAndPassword(
        email: String, password: String
    ){
        _loading.value = true
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(){ task ->
                if (task.isSuccessful) {
                    Log.e("Error", "UserCreatedSuccessfully")
                    _thisUser.value = auth.currentUser
                    _loading.value = false
                } else{
                    _error.value = task.exception?.message.toString()
                    _loading.value = false
                }
            }
    }
}