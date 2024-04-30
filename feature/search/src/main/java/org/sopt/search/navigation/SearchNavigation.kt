package org.sopt.search.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.search.SearchRoute

fun NavController.navigateSearch(navOptions: NavOptions) {
    navigate(SearchRoute.route, navOptions)
}

fun NavGraphBuilder.searchNavGraph(
    modifier: Modifier = Modifier,
) {
    composable(route = SearchRoute.route) {
        SearchRoute(
            modifier = modifier,
            label = ""
        )
    }
}

object SearchRoute {
    const val route = "search"
}