package org.sopt.main.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import org.sopt.designsystem.component.button.RegularButton
import org.sopt.designsystem.component.textfield.RegularTextField
import org.sopt.designsystem.ui.theme.NOWSOPTAndroidTheme

@Composable
fun SignupRoute(
    modifier: Modifier = Modifier,
    viewModel: SignupViewModel = hiltViewModel(),
    navigateLogin: (Boolean) -> Unit,
) {
    val state by viewModel.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            SignupSideEffect.SignupSuccess -> {
                navigateLogin(true)
            }

            is SignupSideEffect.ShowSnackbar -> {
                scope.launch {
                    snackBarHostState.currentSnackbarData?.dismiss()
                    snackBarHostState.showSnackbar(
                        sideEffect.message,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    SignupScreen(
        modifier = modifier,
        snackBarHostState = snackBarHostState,
        state = state,
        onClickSignupBtn = viewModel::signup,
        onValueChangeId = viewModel::updateId,
        onValueChangePw = viewModel::updatePw,
        onValueChangeHobby = viewModel::updatePhone,
        onValueChangeName = viewModel::updateName
    )
}

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
    state: SignupState = SignupState(),
    onClickSignupBtn: () -> Unit = {},
    onValueChangeId: (String) -> Unit = {},
    onValueChangePw: (String) -> Unit = {},
    onValueChangeName: (String) -> Unit = {},
    onValueChangeHobby: (String) -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 50.dp + it.calculateTopPadding(),
                    bottom = 20.dp + it.calculateBottomPadding(),
                    start = 20.dp,
                    end = 20.dp
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Sign Up",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.Black,
                    )
                }

                Spacer(modifier = Modifier.height(70.dp))

                RegularTextField(
                    errorState = state.idValidation,
                    modifier = Modifier.padding(end = 20.dp),
                    title = "ID",
                    textStyle = MaterialTheme.typography.headlineSmall,
                    value = state.id,
                    placeholder = "아이디를 입력하세요",
                    onValueChange = onValueChangeId
                )

                Spacer(modifier = Modifier.height(70.dp))

                RegularTextField(
                    errorState = state.passwordValidation,
                    modifier = Modifier.padding(end = 20.dp),
                    title = "PW",
                    textStyle = MaterialTheme.typography.headlineSmall,
                    value = state.password,
                    placeholder = "비밀번호를 입력하세요",
                    onValueChange = onValueChangePw
                )

                Spacer(modifier = Modifier.height(70.dp))

                RegularTextField(
                    errorState = state.nameValidation,
                    modifier = Modifier.padding(end = 20.dp),
                    title = "이름",
                    textStyle = MaterialTheme.typography.headlineSmall,
                    value = state.name,
                    placeholder = "이름을 입력하세요",
                    onValueChange = onValueChangeName
                )

                Spacer(modifier = Modifier.height(70.dp))

                RegularTextField(
                    errorState = state.phoneValidation,
                    modifier = Modifier.padding(end = 20.dp),
                    title = "휴대폰",
                    textStyle = MaterialTheme.typography.headlineSmall,
                    value = state.phone,
                    placeholder = "휴대폰 번호를 입력하세요",
                    onValueChange = onValueChangeHobby
                )

                Spacer(modifier = Modifier.weight(2f))

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
fun SignupScreenPreview() {
    NOWSOPTAndroidTheme {
        //SignupScreen()
    }
}