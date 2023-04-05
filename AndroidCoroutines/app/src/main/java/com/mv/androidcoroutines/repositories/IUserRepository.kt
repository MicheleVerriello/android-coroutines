package com.mv.androidcoroutines.repositories

import com.mv.androidcoroutines.models.dtos.User
import com.mv.androidcoroutines.models.Result
interface IUserRepository {

    suspend fun getUserById(id: String) : Result<User>
    suspend fun getPasswordByUserId(id: String) : Result<String>
    suspend fun updatePasswordByUserId(id: String, password: String): Result<Boolean>
}