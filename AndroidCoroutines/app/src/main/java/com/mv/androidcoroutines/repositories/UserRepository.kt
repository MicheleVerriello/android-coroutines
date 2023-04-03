package com.mv.androidcoroutines.repositories

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mv.androidcoroutines.constants.CollectionNames
import com.mv.androidcoroutines.models.dtos.User
import kotlinx.coroutines.tasks.await
import java.text.CollationElementIterator

class UserRepository : IUserRepository {

    private val usersCollection = Firebase.firestore.collection(CollectionNames.USERS_COLLECTION)

    override suspend fun getUserById(id: String): User? {
        return usersCollection.document(id)
            .get()
            .await()
            .toObject(User::class.java)
    }
    override suspend fun getPasswordByUserId(id: String): String {
        return usersCollection.document(id)
            .get()
            .await()
            .get("password").toString()
    }

    override suspend fun updatePasswordByUserId(id: String, password: String): Boolean {
        var result = false
        usersCollection
            .document(id)
            .update("password", password)
            .addOnSuccessListener {
                result = true
            }
            .await()
        return result
    }
}