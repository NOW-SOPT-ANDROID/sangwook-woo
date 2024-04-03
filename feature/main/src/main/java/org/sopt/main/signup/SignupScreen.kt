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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import org.sopt.designsystem.component.button.RegularButton
import org.sopt.designsystem.component.textfield.RegularTextField
import org.sopt.designsystem.ui.theme.NOWSOPTAndroidTheme
import org.sopt.main.model.User

@Composable
fun SignupRoute(
    viewModel: SignupViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateLogin: (User) -> Unit,
) {
    val state = viewModel.collectAsState().value
    val snackBarHostState = remember { SnackbarHostState() }
    val sideEffectFlow = viewModel.container.sideEffectFlow
    val scope = rememberCoroutineScope()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is SignupSideEffect.SignupSuccess -> {
                navigateLogin(sideEffect.user)
            }

            is SignupSideEffect.showSnackbar -> {
                snackBarHostState.showSnackbar(
                    sideEffect.message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    SignupScreen(
        snackBarHostState = snackBarHostState,
        state = state,
        onClickSignupBtn = viewModel::signup,
        onValueChangeId = viewModel::updateId,
        onValueChangePw = viewModel::updatePw,
        onValueChangeHobby = viewModel::updateHobby,
        onValueChangeName = viewModel::updateName
    )
}

@Composable
fun SignupScreen(
    snackBarHostState: SnackbarHostState,
    state: SignupState = SignupState(),
    onClickSignupBtn: () -> Unit = {},
    onValueChangeId: (String) -> Unit = {},
    onValueChangePw: (String) -> Unit = {},
    onValueChangeName: (String) -> Unit = {},
    onValueChangeHobby: (String) -> Unit = {},
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { it ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp + it.calculateTopPadding(), bottom = 20.dp + it.calculateBottomPadding(), start = 20.dp, end = 20.dp)
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

                Spacer(modifier = Modifier.height(70.dp))

                RegularTextField(
                    modifier = Modifier.padding(end = 20.dp),
                    title = "이름",
                    textStyle = MaterialTheme.typography.headlineSmall,
                    value = state.name,
                    placeholder = "이름을 입력하세요",
                    onValueChange = onValueChangeName
                )

                Spacer(modifier = Modifier.height(70.dp))

                RegularTextField(
                    modifier = Modifier.padding(end = 20.dp),
                    title = "취미",
                    textStyle = MaterialTheme.typography.headlineSmall,
                    value = state.hobby,
                    placeholder = "취미를 입력하세요",
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