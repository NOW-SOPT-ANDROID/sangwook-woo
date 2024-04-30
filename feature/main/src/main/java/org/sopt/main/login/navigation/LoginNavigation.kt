package org.sopt.main.login.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.main.login.LoginRoute

fun NavController.navigateLogin() {
    navigate(LoginRoute.route) {
        popUpTo(this@navigateLogin.graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavGraphBuilder.loginNavGraph(
    modifier: Modifier = Modifier,
    navigateSignup: () -> Unit = {},
    navigateHome: () -> Unit = {},
) {
    composable(route = LoginRoute.route) {
        LoginRoute(
            navigateHome = navigateHome,
            navigateSignup = navigateSignup
        )
    }
}

object LoginRoute {
    const val route = "login"
}