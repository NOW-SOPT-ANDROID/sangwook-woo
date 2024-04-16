package org.sopt.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.designsystem.component.button.RegularButton
import org.sopt.designsystem.component.textfield.RegularTextField
import org.sopt.designsystem.ui.theme.NOWSOPTAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFriendBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDismissRequest: () -> Unit = {},
    onClickSave: () -> Unit,
    onNameValueChange: (String) -> Unit,
    onHobbyValueChange: (String) -> Unit,
    state: HomeState,
) {
    ModalBottomSheet(sheetState = sheetState, onDismissRequest = onDismissRequest) {
        AddFriendContent(
            onClickSave = onClickSave,
            onNameValueChange = onNameValueChange,
            onHobbyValueChange = onHobbyValueChange,
            state = state
        )
    }
}

@Composable
fun AddFriendContent(
    onClickSave: () -> Unit,
    onNameValueChange: (String) -> Unit = {},
    onHobbyValueChange: (String) -> Unit,
    state: HomeState,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "친구 추가",
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(40.dp))
        RegularTextField(
            value = state.savingName,
            placeholder = "이름을 입력하세요",
            onValueChange = onNameValueChange

        )
        Spacer(modifier = Modifier.height(40.dp))
        RegularTextField(
            value = state.savingHobby,
            placeholder = "취미를 입력하세요",
            onValueChange = onHobbyValueChange
        )
        Spacer(modifier = Modifier.height(40.dp))
        RegularButton(
            modifier = Modifier.padding(horizontal = 40.dp),
            text = "저장",
            onClick = {
                onClickSave()
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Preview
@Composable
fun PreviewBottomSheet() {
    NOWSOPTAndroidTheme {
        //AddFriendContent()
    }
}

