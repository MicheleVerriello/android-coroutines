package com.mv.androidcoroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mv.androidcoroutines.repositories.IUserRepository
import com.mv.androidcoroutines.repositories.UserRepository
import com.mv.androidcoroutines.ui.theme.AndroidCoroutinesTheme
import com.mv.androidcoroutines.ui.views.settings.SettingsScreen
import com.mv.androidcoroutines.ui.views.settings.SettingsViewModel
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"
    val scope = MainScope() + CoroutineName("MainActivity")

    private val userRepository : IUserRepository = UserRepository()
    private val userId = "9XvTHyBur8ejh8BKaAya"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            AndroidCoroutinesTheme {
                    Scaffold(
                        bottomBar = { BottomBar(navController = navController) }
                    ) {
                        NavHost(
                            modifier = Modifier.padding(it),
                            navController = navController,
                            startDestination = "settings"
                        ) {
                            composable("settings") { SettingsScreen(SettingsViewModel(userId)) }
                        }
                    }
            }
        }
    }

  //  override fun onStart() {
    //     super.onStart()
    //    scope.launch {
    //            user.value = userRepository.getUserById("9XvTHyBur8ejh8BKaAya")!!
    //           println(user.toString())
    //    }
    //}
}

@Composable
fun BottomBar(navController: NavController) {

    BottomNavigation(modifier = Modifier.fillMaxWidth()) {
        BottomNavigationItem(
            selected = true,
            onClick = { navController.navigate("settings") },
            icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = null) }
        )
    }

}