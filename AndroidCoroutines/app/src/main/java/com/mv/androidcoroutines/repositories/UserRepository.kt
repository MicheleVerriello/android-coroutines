package com.mv.androidcoroutines.repositories

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mv.androidcoroutines.models.dtos.User
import kotlinx.coroutines.tasks.await

class UserRepository : IUserRepository {

    private val USER_COLLECTION = "users"

    override suspend fun getUserById(id: String): User? {

        return Firebase.firestore
            .collection(USER_COLLECTION)
            .document(id)
            .get()
            .await()
            .toObject(User::class.java)
    }

    override suspend fun getPasswordByUserId(id: String): String {
        return Firebase.firestore
            .collection(USER_COLLECTION)
            .document(id)
            .get()
            .await()
            .get("password").toString()
    }


}