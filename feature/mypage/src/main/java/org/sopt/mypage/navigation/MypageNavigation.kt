package org.sopt.mypage.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.mypage.MypageRoute

fun NavController.navigateMypage(navOptions: NavOptions? = null) {
    navigate(MypageRoute.route, navOptions)
}

fun NavGraphBuilder.MypageNavGraph(
    modifier: Modifier = Modifier,
    navigateToLoginScreen: (Boolean) -> Unit,
    navigateToModifyPassword: () -> Unit,
) {
    composable(route = MypageRoute.route) { navBackStackEntry ->
        MypageRoute(
            modifier = modifier,
            navigateToLoginScreen = navigateToLoginScreen,
            navigateToModifyPassword = navigateToModifyPassword
        )
    }
}

object MypageRoute {
    const val route = "mypage"
}