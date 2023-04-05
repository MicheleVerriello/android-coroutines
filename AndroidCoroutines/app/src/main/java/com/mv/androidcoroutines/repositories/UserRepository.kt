package com.mv.androidcoroutines.repositories

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mv.androidcoroutines.constants.CollectionNames
import com.mv.androidcoroutines.models.dtos.User
import kotlinx.coroutines.tasks.await
import com.mv.androidcoroutines.models.Result

class UserRepository : IUserRepository {

    private val usersCollection = Firebase.firestore.collection(CollectionNames.USERS_COLLECTION)

    override suspend fun getUserById(id: String): Result<User> {
        return try {
            val user = usersCollection.document(id)
                .get()
                .await()
                .toObject(User::class.java)

            if(user != null) {
                Result.Success(user)
            } else {
                throw Exception("No user returned")
            }
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
    override suspend fun getPasswordByUserId(id: String): Result<String> {
        return try {
            val password = usersCollection.document(id)
                .get()
                .await()
                .get("password").toString()

            if (password.isNotEmpty()) {
                return Result.Success(password)
            } else {
                throw Exception("Returned password is empty")
            }
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    override suspend fun updatePasswordByUserId(id: String, password: String): Result<Boolean> {
       return try {
           usersCollection.document(id)
               .update("password", password)
               .await()

           return Result.Success(true)
       } catch (exception: Exception) {
           Result.Error(Exception("error on updating password"))
       }
    }
}