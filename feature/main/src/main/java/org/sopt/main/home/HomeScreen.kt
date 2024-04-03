package org.sopt.main.home

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import org.sopt.designsystem.ui.theme.NOWSOPTAndroidTheme
import org.sopt.main.model.User
import org.sopt.ui.intent.getParcelableSafe

@Composable
fun HomeRoute(
    navController: NavController,
    popBackStack: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
){
    val state = viewModel.collectAsState().value
    val snackBarHostState = remember { SnackbarHostState() }

    viewModel.collectSideEffect {sideEffect ->
        when(sideEffect){
            HomeSideEffect.LoginSuccess -> {
                snackBarHostState.currentSnackbarData?.dismiss()
                snackBarHostState.showSnackbar("로그인 성공")
            }
        }
    }

    LaunchedEffect(true) {
        navController.previousBackStackEntry?.savedStateHandle?.run {
            val bundle = get<Bundle>("user")
            val user = bundle?.getParcelableSafe("user", User::class.java) ?: User()
            viewModel.updateState(user)
        }
    }

    HomeScreen(
        snackBarHostState = snackBarHostState,
        state = state
    )
}
@Composable
fun HomeScreen(
    snackBarHostState: SnackbarHostState,
    state: HomeState
    ) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { it ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp + it.calculateTopPadding(), bottom = 20.dp + it.calculateBottomPadding(), start = 20.dp, end = 20.dp)
        ) {
            Column {
                HomeText(
                    title = "이름 : ",
                    text = state.name
                )
                Spacer(modifier = Modifier.height(30.dp))
                HomeText(
                    title = "아이디 : ",
                    text = state.id
                )
                Spacer(modifier = Modifier.height(30.dp))
                HomeText(
                    title = "비밀번호 : ",
                    text = state.password
                )
                Spacer(modifier = Modifier.height(30.dp))
                HomeText(
                    title = "취미 : ",
                    text = state.hobby
                )
            }
        }
    }
}

@Composable
fun HomeText(
    title: String = "",
    text: String = "",
    style: TextStyle = MaterialTheme.typography.titleLarge,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = style,
            textAlign = TextAlign.Center
        )
        Text(
            text = text,
            style = style,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NOWSOPTAndroidTheme {
        //HomeScreen()
    }
}