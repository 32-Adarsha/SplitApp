package com.example.splitapp.DataLayer.DI

import com.example.splitapp.DataLayer.IRepository.IAuthRepository
import com.example.splitapp.DataLayer.IRepository.IUserDataRepository
import com.example.splitapp.DataLayer.Repository.AuthRepository
import com.example.splitapp.DataLayer.Repository.UserDataRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RealTimeDataBaseDI {

    @Provides
    @Singleton
    fun provideRealTimeDatabase(): FirebaseDatabase = Firebase.database("https://newtest-50df4-default-rtdb.firebaseio.com/")

    @Provides
    @Singleton
    fun provideAuthRepository(database: FirebaseDatabase): IUserDataRepository = UserDataRepository(database)
}