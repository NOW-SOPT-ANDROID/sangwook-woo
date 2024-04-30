package org.sopt.home.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.home.HomeRoute

fun NavController.navigateHome(navOptions: NavOptions? = null) {
    if (navOptions == null)
        navigate(HomeRoute.route)
    else
        navigate(HomeRoute.route, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    modifier: Modifier = Modifier,
) {
    composable(route = HomeRoute.route) {
        HomeRoute(
            modifier = modifier,
            label = ""
        )
    }
}

object HomeRoute {
    const val route = "home"
}