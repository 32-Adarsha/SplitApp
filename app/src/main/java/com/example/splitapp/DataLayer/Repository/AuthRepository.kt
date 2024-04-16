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
    override suspend fun firebaseSignUpWithEmailAndPassword(
        email: String,
        password: String
    ): FirebaseUser? {
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            return auth.currentUser
        } catch(e:Exception) {
            return null
        }
    }

    override suspend fun firebaseSignInWithEmailAndPassword(
        email: String, password: String
    ) :FirebaseUser? {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            return auth.currentUser
        } catch (e: Exception) {
            return null
        }
    }
}