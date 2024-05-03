package org.sopt.mypage.modifypassword

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import org.sopt.designsystem.component.button.RegularButton
import org.sopt.designsystem.component.textfield.RegularTextField

@Composable
fun ModifyPasswordRoute(
    modifier: Modifier = Modifier,
    viewModel: ModifyPasswordViewModel = hiltViewModel(),
    navigateMypage: () -> Unit,
) {
    val state by viewModel.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            ModifyPasswordSideEffect.ModifySuccess -> navigateMypage()
            is ModifyPasswordSideEffect.ShowSnackbar -> {
                scope.launch {
                    snackBarHostState.currentSnackbarData?.dismiss()
                    snackBarHostState.showSnackbar(
                        sideEffect.msg,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    ModifyPasswordScreen(
        modifier = modifier,
        snackbarHostState = snackBarHostState,
        state = state,
        onClickModifyButton = viewModel::modifyPassword,
        onValueChangePreviousPassword = viewModel::updatePreviousPassword,
        onValueChangeNewPassword = viewModel::updateNewPassword,
        onValueChangeNewPasswordVerification = viewModel::updateNewPasswordVerification
    )
}

@Composable
fun ModifyPasswordScreen(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    state: ModifyPasswordState,
    onClickModifyButton: () -> Unit,
    onValueChangePreviousPassword: (String) -> Unit,
    onValueChangeNewPassword: (String) -> Unit,
    onValueChangeNewPasswordVerification: (String) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
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
                        text = "비밀번호 변경",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.Black,
                    )
                }

                Spacer(modifier = Modifier.height(70.dp))

                RegularTextField(
                    modifier = Modifier.padding(end = 20.dp),
                    textStyle = MaterialTheme.typography.headlineSmall,
                    value = state.previousPassword,
                    placeholder = "현재 비밀번호",
                    onValueChange = onValueChangePreviousPassword
                )

                Spacer(modifier = Modifier.height(70.dp))

                RegularTextField(
                    modifier = Modifier.padding(end = 20.dp),
                    textStyle = MaterialTheme.typography.headlineSmall,
                    value = state.newPassword,
                    placeholder = "변경할 비밀번호",
                    onValueChange = onValueChangeNewPassword
                )

                Spacer(modifier = Modifier.height(70.dp))

                RegularTextField(
                    modifier = Modifier.padding(end = 20.dp),
                    textStyle = MaterialTheme.typography.headlineSmall,
                    value = state.newPasswordVerification,
                    placeholder = "비밀번호 확인",
                    onValueChange = onValueChangeNewPasswordVerification
                )

                Spacer(modifier = Modifier.weight(2f))

                RegularButton(
                    text = "비밀번호 변경",
                    clickable = true,
                    onClick = onClickModifyButton
                )
            }
        }
    }
}