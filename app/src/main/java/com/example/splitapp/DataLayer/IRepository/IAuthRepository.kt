package com.example.splitapp.DataLayer.IRepository

import com.google.android.gms.common.api.Response
import com.google.firebase.auth.FirebaseUser
import java.util.function.BiPredicate

interface IAuthRepository {
    var user:FirebaseUser?
    suspend fun firebaseSignUpWithEmailAndPassword(email: String, password: String): Boolean
    suspend fun firebaseSignInWithEmailAndPassword(email: String, password: String) :Boolean

}