package org.sopt.main.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.main.signup.SignupRoute

fun NavController.navigateSignup() {
    navigate(SignupRoute.route)
}

fun NavGraphBuilder.signupNavGraph(
    navigateLogin: () -> Unit = {},
) {
    composable(route = SignupRoute.route) { navBackStackEntry ->
        SignupRoute(
            navigateLogin = navigateLogin
        )
    }
}

object SignupRoute {
    const val route = "signup"
}