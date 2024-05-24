package org.sopt.home

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import org.sopt.model.ReqresUser


@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    label: String,
) {
    val postState = viewModel.users.collectAsLazyPagingItems()

    HomeScreen(
        modifier = modifier,
        postItem = postState,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    postItem: LazyPagingItems<ReqresUser>,
) {
    val listState = rememberLazyListState()
    Box(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = listState
        ) {
            items(postItem.itemCount) { index ->
                postItem[index]?.let { reqres ->
                    ReqresContainer(
                        modifier = Modifier.animateItemPlacement(
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = LinearOutSlowInEasing,
                            )
                        ),
                        id = reqres.id,
                        email = reqres.email,
                        imageUri = reqres.avatar,
                        firstName = reqres.firstName,
                        lastName = reqres.lastName,
                        containerHeight = 80.dp,
                    )
                }
            }
        }
    }
}


/*@Preview
@Composable
fun preview() {
    NOWSOPTAndroidTheme {
        HomeScreen(
            state = HomeState(
            )
        )
    }
}*/