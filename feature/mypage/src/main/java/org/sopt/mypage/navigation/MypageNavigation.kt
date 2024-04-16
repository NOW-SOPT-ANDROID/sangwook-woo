package org.sopt.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.mypage.MypageRoute

fun NavController.navigateMypage(navOptions: NavOptions) {
    navigate(MypageRoute.route, navOptions)
}

fun NavGraphBuilder.MypageNavGraph(
    navigateToLoginScreen: () -> Unit,
) {
    composable(route = MypageRoute.route) { navBackStackEntry ->
        MypageRoute(
            navigateToLoginScreen = navigateToLoginScreen
        )
    }
}

object MypageRoute {
    const val route = "mypage"
}