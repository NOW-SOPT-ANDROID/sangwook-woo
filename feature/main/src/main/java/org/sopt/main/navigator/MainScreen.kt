package org.sopt.main.navigator

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.sopt.main.home.navigation.homeNavGraph
import org.sopt.main.login.navigation.loginNavGraph
import org.sopt.main.signup.navigation.signupNavGraph

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigator: MainNavigator = rememberMainNavigator(),
) {
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        content = { it ->
            val padding = it
            NavHost(
                navController = navigator.navController,
                startDestination = navigator.startDestination,
            ) {
                loginNavGraph(
                    navController = navigator.navController,
                    popBackStack = navigator::popBackStackIfNotHome,
                    navigateSignup = navigator::navigateSignup,
                    navigateHome = navigator::navigateHome
                )

                signupNavGraph(
                    popBackStack = navigator::popBackStackIfNotHome,
                    navigateLogin = navigator::navigateLogin
                )

                homeNavGraph(
                    navController = navigator.navController,
                    popBackStack = navigator::popBackStackIfNotHome,
                )
            }
        }
    )
}