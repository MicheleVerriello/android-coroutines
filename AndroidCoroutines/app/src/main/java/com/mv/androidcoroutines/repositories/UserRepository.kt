package com.mv.androidcoroutines.repositories

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mv.androidcoroutines.models.dtos.User
import kotlinx.coroutines.tasks.await

class UserRepository : IUserRepository {

    override suspend fun getUserById(id: String): User? {

        return Firebase.firestore
            .collection("users")
            .document(id)
            .get()
            .await()
            .toObject(User::class.java)
    }
}