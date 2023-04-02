package com.mv.androidcoroutines.models.dtos

data class User(
    val id: String = "",
    val name: String = "",
    val surname: String = "",
    val gender: Gender = Gender.NOT_SPECIFIED,
    val email: String = "",
    val birthdayDate: Date = Date(0, 0, 0)
)
