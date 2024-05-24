package org.sopt.main.signup.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.main.signup.SignupRoute

fun NavController.navigateSignup() {
    navigate(SignupRoute.route)
}

fun NavGraphBuilder.signupNavGraph(
    modifier: Modifier = Modifier,
    navigateLogin: (Boolean) -> Unit = {},
) {
    composable(route = SignupRoute.route) { navBackStackEntry ->
        SignupRoute(
            modifier = modifier,
            navigateLogin = navigateLogin
        )
    }
}

object SignupRoute {
    const val route = "signup"
}