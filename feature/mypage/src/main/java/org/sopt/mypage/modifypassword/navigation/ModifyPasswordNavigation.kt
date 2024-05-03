package org.sopt.mypage.modifypassword.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.mypage.modifypassword.ModifyPasswordRoute

fun NavController.navigateModifyPassword() {
    navigate(ModifyPasswordRoute.route)
}

fun NavGraphBuilder.modifyPasswordNavGraph(
    modifier: Modifier = Modifier,
    navigateMypage: () -> Unit = {},
) {
    composable(route = ModifyPasswordRoute.route) { navBackStackEntry ->
        ModifyPasswordRoute(
            modifier = modifier,
            navigateMypage = navigateMypage
        )
    }
}

object ModifyPasswordRoute {
    const val route = "modifyPassword"
}