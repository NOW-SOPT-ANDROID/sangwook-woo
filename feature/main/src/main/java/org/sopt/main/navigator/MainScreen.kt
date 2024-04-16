package org.sopt.main.navigator

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import okhttp3.internal.toImmutableList
import org.sopt.home.navigation.homeNavGraph
import org.sopt.main.login.navigation.loginNavGraph
import org.sopt.main.signup.navigation.signupNavGraph
import org.sopt.mypage.navigation.MypageNavGraph
import org.sopt.search.navigation.searchNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigator: MainNavigator = rememberMainNavigator(),
) {
    Scaffold(
        modifier = modifier,
        content = {
            NavHost(
                navController = navigator.navController,
                startDestination = navigator.startDestination,
            ) {
                loginNavGraph(
                    navigateSignup = navigator::navigateSignup,
                    navigateHome = navigator::navigateHome,
                )

                signupNavGraph(
                    navigateLogin = navigator::navigateLogin
                )

                MypageNavGraph(
                    navigateToLoginScreen = navigator::navigateLogin
                )

                homeNavGraph()

                searchNavGraph()
            }
        },
        bottomBar = {
            MainBottomNav(
                visible = navigator.shouldShowBottomBar(),
                tabs = MainTab.entries.toImmutableList(),
                currentTab = navigator.currentTab,
                onTabSelected = navigator::navigate,
            )
        }
    )
}

@Composable
private fun MainBottomNav(
    visible: Boolean,
    tabs: List<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(
                    color = White,
                ),
            horizontalArrangement = Arrangement.Center
        ) {
            tabs.forEach { tab ->
                MainBottomBarItem(
                    tab = tab,
                    selected = tab == currentTab,
                    onClick = { onTabSelected(tab) }
                )
            }
        }
    }
}

@Composable
private fun RowScope.MainBottomBarItem(
    tab: MainTab,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .clickable(
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = tab.iconResId),
            contentDescription = tab.contentDescription,
            tint = if (selected) Black else Gray,
            modifier = Modifier.size(24.dp)
        )
    }
}