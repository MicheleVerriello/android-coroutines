package com.mv.androidcoroutines.repositories

import com.mv.androidcoroutines.models.dtos.User

interface IUserRepository {

    suspend fun getUserById(id: String) : User?
    suspend fun getPasswordByUserId(id: String) : String
    suspend fun updatePasswordByUserId(id: String, password: String): Boolean
}