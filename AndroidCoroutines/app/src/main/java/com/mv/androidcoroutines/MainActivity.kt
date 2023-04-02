package com.mv.androidcoroutines

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mv.androidcoroutines.models.dtos.Date
import com.mv.androidcoroutines.models.dtos.Gender
import com.mv.androidcoroutines.models.dtos.User
import com.mv.androidcoroutines.repositories.IUserRepository
import com.mv.androidcoroutines.repositories.UserRepository
import com.mv.androidcoroutines.ui.theme.AndroidCoroutinesTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"

    private val userRepository : IUserRepository = UserRepository()
    var user = mutableStateOf(
        User(id = "", name = "", surname = "", email = "", gender = Gender.NOT_SPECIFIED, birthdayDate = Date(1,1,1))
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidCoroutinesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainActivityView(user)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            user.value = userRepository.getUserById("9XvTHyBur8ejh8BKaAya")!!
            println(user.toString())
        }
    }
}

@Composable
fun MainActivityView(user: MutableState<User>) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = user.value.toString())
    }

}