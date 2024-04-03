package org.sopt.main.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.main.model.User
import org.sopt.main.signup.SignupRoute

fun NavController.navigateSignup() {
    navigate(SignupRoute.route)
}

fun NavGraphBuilder.signupNavGraph(
    popBackStack: () -> Unit = {},
    navigateLogin: (User) -> Unit = {},
){
    composable(route = SignupRoute.route) { navBackStackEntry ->
        SignupRoute(
            popBackStack = popBackStack,
            navigateLogin = navigateLogin
        )
    }
}

object SignupRoute {
    const val route = "signup"
}