package org.sopt.main.navigator

import org.sopt.designsystem.R

enum class MainTab(
    val iconResId: Int,
    val contentDescription: String,
    val route: String,
) {
    HOME(
        iconResId = R.drawable.ic_home_24,
        contentDescription = "Home",
        route = "home"
    ),
    SEARCH(
        iconResId = R.drawable.ic_search_24,
        contentDescription = "Search",
        route = "search"
    ),
    MY_PAGE(
        iconResId = R.drawable.ic_person_24,
        contentDescription = "Mypage",
        route = "mypage"
    )
    ;

    companion object {
        operator fun contains(route: String): Boolean {
            return entries.map { it.route }.contains(route)
        }

        fun find(route: String): MainTab? {
            return entries.find { it.route == route }
        }
    }
}