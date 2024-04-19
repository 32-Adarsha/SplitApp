package com.example.splitapp.DataLayer.IRepository

import com.google.android.gms.common.api.Response
import com.google.firebase.auth.FirebaseUser
import java.util.function.BiPredicate

interface IAuthRepository {
    fun firebaseSignUpWithEmailAndPassword(email: String, password: String)
    fun firebaseSignInWithEmailAndPassword(email: String, password: String)

}