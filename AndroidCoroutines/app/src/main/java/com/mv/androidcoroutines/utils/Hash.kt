package com.mv.androidcoroutines.utils

import java.security.MessageDigest
import java.util.*

fun hashPassword(password: String) : String {
    val bytes = password.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return Base64.getEncoder().encodeToString(digest)
}