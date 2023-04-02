package com.mv.androidcoroutines.repositories

import com.mv.androidcoroutines.models.dtos.User

interface IUserRepository {
    suspend fun getUserById(id: String) : User?
}