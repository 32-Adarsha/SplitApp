package com.example.splitapp.DataLayer.Repository

import com.example.splitapp.DataLayer.IRepository.IAuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class AuthRepository @Inject constructor(
    private val auth:FirebaseAuth
) : IAuthRepository {
    override var user: FirebaseUser?= null
        get() = field
        set(value) {field = value}
    override suspend fun firebaseSignUpWithEmailAndPassword(
        email: String,
        password: String
    ): Boolean {
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            user = auth.currentUser
            return true
        } catch(e:Exception) {
            return false
        }
    }

    override suspend fun firebaseSignInWithEmailAndPassword(
        email: String, password: String
    ) :Boolean {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            auth.currentUser
            return true
        } catch (e: Exception) {
            return false
        }
    }
}