package org.sopt.main.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.home.navigation.navigateHome
import org.sopt.main.login.navigation.LoginRoute
import org.sopt.main.login.navigation.navigateLogin
import org.sopt.main.signup.navigation.navigateSignup
import org.sopt.mypage.modifypassword.navigation.navigateModifyPassword
import org.sopt.mypage.navigation.navigateMypage
import org.sopt.search.navigation.navigateSearch

class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = LoginRoute.loginRoute("{${LoginRoute.IS_SIGNUP_SUCESS}}")

    val currentTab: MainTab?
        @Composable get() = currentDestination
            ?.route
            ?.let(MainTab::find)

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        navController.popBackStack(tab.route, false)

        when (tab) {
            MainTab.HOME -> navController.navigateHome(navOptions)
            MainTab.SEARCH -> navController.navigateSearch(navOptions)
            MainTab.MY_PAGE -> navController.navigateMypage(navOptions)
        }
    }

    fun navigateLogin(isSignupSuccess: Boolean) {
        navController.navigateLogin(isSignupSuccess)
    }

    fun navigateSignup() {
        navController.navigateSignup()
    }

    fun navigateHome(navOptions: NavOptions? = null) {
        navController.navigateHome(navOptions)
    }

    fun navigateModifyPassword() {
        navController.navigateModifyPassword()
    }

    fun navigateMypage(navOptions: NavOptions? = null) {
        navController.navigateMypage(navOptions)
    }

    fun popBackStackIfNotLogin() {
        if (!isSameCurrentDestination(LoginRoute.route)) {
            navController.popBackStack()
        }
    }

    private fun isSameCurrentDestination(route: String) =
        navController.currentDestination?.route == route

    @Composable
    fun shouldShowBottomBar(): Boolean {
        val currentRoute = currentDestination?.route ?: return false
        return currentRoute in MainTab
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}