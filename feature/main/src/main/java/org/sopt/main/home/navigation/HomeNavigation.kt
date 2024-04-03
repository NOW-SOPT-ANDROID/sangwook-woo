package org.sopt.main.home.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.main.home.HomeRoute
import org.sopt.main.model.User

fun NavController.navigateHome(user: User) {
    val bundle = Bundle().apply {
        putParcelable("user", user)
    }
    navigate(HomeRoute.route){
        currentBackStackEntry?.savedStateHandle?.set("user", bundle)
    }
}

fun NavGraphBuilder.homeNavGraph(
    navController: NavController,
    popBackStack: () -> Unit,
){
    composable(route = HomeRoute.route) { navBackStackEntry ->
        HomeRoute(
            navController = navController,
            popBackStack = popBackStack,
        )
    }
}

object HomeRoute {
    const val route = "home"
}