package org.sopt.main.login

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import org.sopt.designsystem.component.button.RegularButton
import org.sopt.designsystem.component.textfield.RegularTextField
import org.sopt.designsystem.ui.theme.NOWSOPTAndroidTheme
import org.sopt.main.model.User
import org.sopt.ui.intent.getParcelableSafe

@Composable
fun LoginRoute(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateHome: (User) -> Unit,
    navigateSignup: () -> Unit,
){
    val state = viewModel.collectAsState().value
    val snackBarHostState = remember { SnackbarHostState() }

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is LoginSideEffect.NavigateToSignUp -> {
                navigateSignup()
            }

            is LoginSideEffect.LoginSuccess -> {
                navigateHome(sideEffect.user)
            }
            LoginSideEffect.SignupSuccess -> {
                snackBarHostState.currentSnackbarData?.dismiss()
                snackBarHostState.showSnackbar("회원가입 성공")
            }
            is LoginSideEffect.showSnackbar -> {
                snackBarHostState.currentSnackbarData?.dismiss()
                snackBarHostState.showSnackbar(sideEffect.message)
            }
        }
    }

    LaunchedEffect(true) {
        navController.previousBackStackEntry?.savedStateHandle?.run {
            val bundle = get<Bundle>("user")
            val user = bundle?.getParcelableSafe("user", User::class.java)
            viewModel.signupSuccess(user)
        }
    }

    LoginScreen(
        snackBarHostState = snackBarHostState,
        state = state,
        onClickLoginBtn = viewModel::login,
        onClickSignupBtn = viewModel::signup,
        onValueChangeId = viewModel::updateId,
        onValueChangePw = viewModel::updatePw
    )
}

@Composable
fun LoginScreen(
    snackBarHostState: SnackbarHostState,
    state: LoginState = LoginState(),
    onClickLoginBtn: () -> Unit = {},
    onClickSignupBtn: () -> Unit = {},
    onValueChangeId: (String) -> Unit = {},
    onValueChangePw: (String) -> Unit = {}
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ){ it ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp + it.calculateTopPadding(), bottom = 20.dp + it.calculateBottomPadding(), start = 20.dp, end = 20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box (
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Welcome To Sopt",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.Black,
                    )
                }

                Spacer(modifier = Modifier.height(70.dp))

                RegularTextField(
                    modifier = Modifier.padding(end = 20.dp),
                    title = "ID",
                    textStyle = MaterialTheme.typography.headlineSmall,
                    value = state.id,
                    placeholder = "아이디를 입력하세요",
                    onValueChange = onValueChangeId
                )

                Spacer(modifier = Modifier.height(70.dp))

                RegularTextField(
                    modifier = Modifier.padding(end = 20.dp),
                    title = "PW",
                    textStyle = MaterialTheme.typography.headlineSmall,
                    value = state.password,
                    placeholder = "비밀번호를 입력하세요",
                    onValueChange = onValueChangePw
                )

                Spacer(modifier = Modifier.weight(2f))

                RegularButton(
                    text = "로그인",
                    clickable = true,
                    onClick = onClickLoginBtn
                )

                Spacer(modifier = Modifier.height(30.dp))

                RegularButton(
                    text = "회원가입",
                    clickable = true,
                    onClick = onClickSignupBtn
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    NOWSOPTAndroidTheme {
        //LoginScreen()
    }
}