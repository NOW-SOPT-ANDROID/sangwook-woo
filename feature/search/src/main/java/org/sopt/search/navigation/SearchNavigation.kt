package org.sopt.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.search.SearchRoute

fun NavController.navigateSearch(navOptions: NavOptions) {
    navigate(SearchRoute.route, navOptions)
}

fun NavGraphBuilder.searchNavGraph(
) {
    composable(route = SearchRoute.route) {
        SearchRoute(
            label = ""
        )
    }
}

object SearchRoute {
    const val route = "search"
}