package com.mv.androidcoroutines.ui.views.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mv.androidcoroutines.models.dtos.User
import com.mv.androidcoroutines.repositories.IUserRepository
import com.mv.androidcoroutines.repositories.UserRepository
import kotlinx.coroutines.launch
import com.mv.androidcoroutines.models.Result
import com.mv.androidcoroutines.utils.hashPassword

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

    private var _showOkDialog by mutableStateOf(false)
    val showOkDialog: Boolean
        get() = _showOkDialog

    private var _showBadDialog by mutableStateOf(false)
    val showBadDialog: Boolean
        get() = _showBadDialog

    //repositories
    private val userRepository : IUserRepository = UserRepository()

    private var actualPassword = ""

    private var user: User? = null

    /*private suspend fun getActualPassword() {
        actualPassword = userRepository.getPasswordByUserId(id)
    }*/

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

    /*fun changePassword() {

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
    }*/

    fun changePassword() {

        // Create a new coroutine on the UI thread
        viewModelScope.launch {

            println("form actual Password = ${hashPassword(_formActualPassword)}")
            println("actual Password = $_newPassword")
            println("actual Password = $_newRepeatedPassword")

            val actualPasswordResponse = userRepository.getPasswordByUserId(id)
            // Display result of the network request to the user
            when (actualPasswordResponse) {
                is Result.Success<String> -> {
                    val newHashedPassword = hashPassword(_newPassword)
                    println("actual Password = ${actualPasswordResponse.data}")
                    if(actualPasswordResponse.data == hashPassword(_formActualPassword)) {
                        when (userRepository.updatePasswordByUserId(id, newHashedPassword)) {
                            is Result.Success<Boolean> -> _showOkDialog = true
                            else -> _showBadDialog = true
                        }
                    } else {
                        _showBadDialog = true
                    }

                }
                else -> _showBadDialog = true
            }
        }
    }
}