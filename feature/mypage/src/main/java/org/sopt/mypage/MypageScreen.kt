package org.sopt.mypage

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import org.sopt.designsystem.component.button.RegularButton
import org.sopt.designsystem.ui.theme.NOWSOPTAndroidTheme

@Composable
fun MypageRoute(
    modifier: Modifier = Modifier,
    viewModel: MypageViewModel = hiltViewModel(),
    navigateToLoginScreen: (Boolean) -> Unit,
    navigateToModifyPassword: () -> Unit,
) {
    val state by viewModel.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            MypageSideEffect.LogoutSuccess -> {
                navigateToLoginScreen(false)
            }

            MypageSideEffect.NavigateModifyPassword -> {
                navigateToModifyPassword()
            }
        }
    }

    MypageScreen(
        snackBarHostState = snackBarHostState,
        modifier = modifier,
        onClickLogoutButton = viewModel::logout,
        onClickWithDrawButton = viewModel::modifyPassword,
        state = state
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MypageScreen(
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onClickLogoutButton: () -> Unit = {},
    onClickWithDrawButton: () -> Unit = {},
    state: MypageState,
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 20.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
        ) {
            Column {
                MypageText(
                    title = "닉네임 : ",
                    text = state.nickname
                )
                Spacer(modifier = Modifier.height(30.dp))
                MypageText(
                    title = "번호 : ",
                    text = state.phone
                )
                Spacer(modifier = Modifier.weight(1f))
                RegularButton(
                    text = "로그아웃",
                    onClick = onClickLogoutButton
                )
                Spacer(modifier = Modifier.height(20.dp))
                RegularButton(
                    text = "비밀번호 변경",
                    onClick = onClickWithDrawButton
                )
                Spacer(modifier = Modifier.height(30.dp))
                Spacer(modifier = Modifier.height(56.dp))
            }
        }
    }
}

@Composable
fun MypageText(
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
fun MypageScreenPreview() {
    NOWSOPTAndroidTheme {
        //MypageRoute()
    }
}