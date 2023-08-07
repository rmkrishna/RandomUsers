package com.abitztech.randomusers.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.abitztech.randomusers.ui.screens.users.UsersScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = USER_SCREEN
    ) {
        composable(USER_SCREEN) {
            UsersScreen {
                
            }
        }

        composable(USER_DETAIL_SCREEN) {

        }
    }
}