package org.sopt.main.login.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.main.login.LoginRoute
import org.sopt.main.login.navigation.LoginRoute.IS_SIGNUP_SUCESS
import org.sopt.main.login.navigation.LoginRoute.loginRoute

fun NavController.navigateLogin(isSignupSuccess: Boolean) {
    navigate(loginRoute(isSignupSuccess.toString())) {
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
    composable(
        route = loginRoute("{${IS_SIGNUP_SUCESS}}"),
        arguments = listOf(
            navArgument(IS_SIGNUP_SUCESS) {
                type = NavType.StringType
            },
        ),
    ) {
        LoginRoute(
            navigateHome = navigateHome,
            navigateSignup = navigateSignup,
        )
    }
}

object LoginRoute {
    const val route = "login"
    const val IS_SIGNUP_SUCESS = "isSignupSuccess"
    fun loginRoute(isSignupSuccess: String) = "login/$isSignupSuccess"
}