package com.mv.androidcoroutines.ui.views.settings

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mv.androidcoroutines.models.dtos.User
import com.mv.androidcoroutines.repositories.IUserRepository
import com.mv.androidcoroutines.repositories.UserRepository
import com.mv.androidcoroutines.utils.hashPassword
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SettingsViewModel(val id: String): ViewModel() {

    // Expose screen UI state
    private var _newPassword by mutableStateOf("")
    val newPassword: String
        get() = _newPassword

    private var _formActualPassword by mutableStateOf("")
    val formActualPassowrd: String
        get() = _formActualPassword

    private var _newRepeatedPassword by mutableStateOf("")
    val newRepeatedPassword: String
        get() = _newRepeatedPassword

    //repositories
    private val userRepository : IUserRepository = UserRepository()

    private var actualPassword = ""

    private var user: User? = null

    private suspend fun getActualPassword() {
        actualPassword = userRepository.getPasswordByUserId(id)
    }

    fun setNewPassword(newPassword: String) {
        _newPassword = newPassword
    }

    fun setRepeatNewPassword(repeatNewPassword: String) {
        _newRepeatedPassword = repeatNewPassword
    }

    fun setActualPassword(actualPassword: String) {
        _formActualPassword = actualPassword
    }

    fun getUser() {
    }

    suspend fun changePassword() = coroutineScope {

        val job1 = viewModelScope.launch {
            getActualPassword()
        }

         val job2 = viewModelScope.launch {
            if(hashPassword(_newPassword) == actualPassword && newRepeatedPassword == newPassword) {
                val result = userRepository.updatePasswordByUserId(id = id, password = _newPassword)
                Log.d("changePassword", result.toString())
            }
        }

        job1.join()
        job2.join()
    }
}