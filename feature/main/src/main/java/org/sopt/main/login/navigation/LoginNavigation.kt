package org.sopt.main.login.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.main.login.LoginRoute
import org.sopt.main.model.User

fun NavController.navigateLogin(user: User) {
    val bundle = Bundle().apply {
        putParcelable("user", user)
    }
    navigate(LoginRoute.route){
        currentBackStackEntry?.savedStateHandle?.set("user", bundle)
    }
}

fun NavGraphBuilder.loginNavGraph(
    navController: NavController,
    popBackStack: () -> Unit = {},
    navigateSignup: () -> Unit = {},
    navigateHome: (User) -> Unit = {},
){
    composable(route = LoginRoute.route) { navBackStackEntry ->
        LoginRoute(
            navController = navController,
            popBackStack = popBackStack,
            navigateHome = navigateHome,
            navigateSignup = navigateSignup
        )
    }
}

object LoginRoute {
    const val route = "login"
}