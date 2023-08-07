package com.abitztech.randomusers.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.abitztech.randomusers.ui.screens.detail.UserDetailScreen
import com.abitztech.randomusers.ui.screens.users.UsersScreen

const val ID = "id"

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = USER_SCREEN
    ) {
        composable(USER_SCREEN) {
            UsersScreen {
                navController.navigate("$USER_DETAIL_SCREEN?$ID=$it")
            }
        }

        composable("$USER_DETAIL_SCREEN?$ID={$ID}", arguments = listOf(navArgument(ID) {
            defaultValue = ""
            type = NavType.StringType
        })) {
            val id = it.arguments?.getString(ID) ?: ""
            UserDetailScreen(id)
        }
    }
}