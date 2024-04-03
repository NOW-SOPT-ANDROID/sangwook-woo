package org.sopt.main.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.main.home.navigation.navigateHome
import org.sopt.main.login.navigation.LoginRoute
import org.sopt.main.login.navigation.navigateLogin
import org.sopt.main.model.User
import org.sopt.main.signup.navigation.navigateSignup

class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = LoginRoute.route

    fun navigateLogin(user: User) {
        navController.navigateLogin(user)
    }

    fun navigateSignup() {
        navController.navigateSignup()
    }

    fun navigateHome(user: User) {
        navController.navigateHome(user)
    }

    fun popBackStackIfNotHome() {
        if (!isSameCurrentDestination(LoginRoute.route)) {
            navController.popBackStack()
        }
    }

    private fun isSameCurrentDestination(route: String) =
        navController.currentDestination?.route == route
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}