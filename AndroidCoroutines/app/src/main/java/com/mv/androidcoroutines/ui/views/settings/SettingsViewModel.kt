package com.mv.androidcoroutines.ui.views.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mv.androidcoroutines.repositories.IUserRepository
import com.mv.androidcoroutines.repositories.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class PasswordChangeUiState(
    val newPassword: String? = null,
    val repeatNewPassword: String? = null
)


class SettingsViewModel(id: String): ViewModel() {

    // Expose screen UI state
    private val _passwordChangeUiState = MutableStateFlow(PasswordChangeUiState())
    val uiState: StateFlow<PasswordChangeUiState> = _passwordChangeUiState.asStateFlow()

    private val userRepository : IUserRepository = UserRepository()




    val actualPassword = viewModelScope.launch {
        userRepository.getPasswordByUserId(id)
    }
}