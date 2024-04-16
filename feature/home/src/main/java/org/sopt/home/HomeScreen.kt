package org.sopt.home

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.sopt.designsystem.component.dialog.SoptAlertDialog
import org.sopt.designsystem.component.textfield.RegularTextField
import org.sopt.designsystem.ui.theme.NOWSOPTAndroidTheme
import org.sopt.model.Friend

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    label: String,
) {
    val state = viewModel.collectAsState().value
    val listState = rememberLazyListState()
    val sheetState = rememberModalBottomSheetState()

    HomeScreen(
        state = state,
        listState = listState,
        sheetState = sheetState,
        onQueryValueChange = viewModel::updateQuery,
        onLongClickItem = viewModel::showDeleteDialog,
        onClickFloatingButton = viewModel::showAddFriendBottomSheet,
        onClickSave = viewModel::insertFriend,
        onBottomSheetNameChange = viewModel::updateSavingName,
        onBottomSheetHobbyChange = viewModel::updateSavingHobby,
        onDismissRequest = viewModel::dismissAddFriendBottomSheet,
        onClickDialogPositiveButton = viewModel::deleteFriend,
        onClickDialogNegativeButton = viewModel::dismissDeleteDialog
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    state: HomeState = HomeState(),
    listState: LazyListState = rememberLazyListState(),
    sheetState: SheetState = rememberModalBottomSheetState(),
    onQueryValueChange: (String) -> Unit = {},
    onLongClickItem: (Int) -> Unit = {},
    onClickFloatingButton: () -> Unit = {},
    onClickSave: () -> Unit = {},
    onBottomSheetNameChange: (String) -> Unit = {},
    onBottomSheetHobbyChange: (String) -> Unit = {},
    onDismissRequest: () -> Unit = {},
    onClickDialogPositiveButton: (Int) -> Unit = {},
    onClickDialogNegativeButton: () -> Unit = {},
) {
    Box {
        if (state.showDeleteDialog.first) {
            SoptAlertDialog(
                title = "삭제?",
                positiveLabel = "삭제",
                negativeLabel = "취소",
                onClickPositive = { onClickDialogPositiveButton(state.showDeleteDialog.second) },
                onClickNegative = onClickDialogNegativeButton,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White),
        ) {
            RegularTextField(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                    .height(40.dp),
                value = state.query,
                onValueChange = onQueryValueChange,
                placeholder = "검색어를 입력해주세요"
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                state = listState
            ) {
                items(
                    items = listOf(
                        Friend(
                            id = 0,
                            name = state.registeredName,
                            hobby = state.registeredHobby
                        )
                    ) + state.friendList,
                    key = { it.id!! }
                ) { friend ->
                    HomeFriendContainer(
                        modifier = Modifier.animateItemPlacement(
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = LinearOutSlowInEasing,
                            )
                        ),
                        id = friend.id,
                        title = friend.name,
                        subTitle = friend.hobby,
                        containerHeight = 80.dp,
                        onLongClick = { onLongClickItem(friend.id!!) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(56.dp))
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
                .padding(bottom = 76.dp),
            onClick = onClickFloatingButton,
        ) {
            Icon(Icons.Filled.Add, "floatingBtn")
        }
        if (state.showBottomSheet) {
            AddFriendBottomSheet(
                sheetState = sheetState,
                onClickSave = onClickSave,
                onDismissRequest = onDismissRequest,
                onNameValueChange = onBottomSheetNameChange,
                onHobbyValueChange = onBottomSheetHobbyChange,
                state = state
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun preview() {
    NOWSOPTAndroidTheme {
        HomeScreen(
            state = HomeState(
                friendList = listOf(
                    Friend(1, "asd", "asd"),
                    Friend(2, "asd", "asd"),
                    Friend(3, "asd", "asd"),
                    Friend(4, "asd", "asd"),
                    Friend(5, "asd", "asd"),
                    Friend(6, "asd", "asd"),
                    Friend(7, "asd", "asd"),
                    Friend(8, "asd", "asd"),
                    Friend(9, "asd", "asd"),
                    Friend(10, "asd", "asd"),
                    Friend(11, "asd", "asd"),
                    Friend(12, "asd", "asd"),
                    Friend(13, "asd", "asd"),
                    Friend(14, "asd", "asd"),
                    Friend(15, "asd", "asd"),
                )
            )
        )
    }
}