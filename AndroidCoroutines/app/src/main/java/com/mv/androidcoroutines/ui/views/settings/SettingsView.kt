package com.mv.androidcoroutines.ui.views.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {

    val coroutineScope = rememberCoroutineScope()

    val modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)

    Column {
        TextField(
            modifier = modifier,
            value = viewModel.formActualPassowrd,
            onValueChange = { viewModel.setActualPassword(it) },
            placeholder = { Text(text = "Actual Password") }
        )

        TextField(
            modifier = modifier,
            value = viewModel.newPassword,
            onValueChange = { viewModel.setNewPassword(it) },
            placeholder = { Text(text = "New Password") }
        )

        TextField(
            modifier = modifier,
            value = viewModel.newRepeatedPassword,
            onValueChange = { viewModel.setRepeatNewPassword(it) },
            placeholder = { Text(text = "Repeat New Password") }
        )

        Button(
            modifier = modifier,
            onClick = { viewModel.changePassword() }
        ) {
            Text(text = "Change Password")
        }

        if(viewModel.showBadDialog) {
            Text(text = "Bad")
        }

        if(viewModel.showOkDialog) {
            Text(text = "Good")
        }
    }
}