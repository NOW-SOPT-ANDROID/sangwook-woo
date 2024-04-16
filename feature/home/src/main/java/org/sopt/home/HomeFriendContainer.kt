package org.sopt.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.designsystem.R
import org.sopt.designsystem.ui.theme.NOWSOPTAndroidTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeFriendContainer(
    id: Int? = 0,
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(R.drawable.ic_launcher_background),
    title: String,
    subTitle: String = "",
    containerHeight: Dp,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(
                if (id == 0) 100.dp else containerHeight
            )
            .background(Color.White)
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick,
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painter, "")
        Text(
            modifier = modifier
                .padding(start = 20.dp),
            text = title,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = subTitle,
            fontSize = 16.sp
        )
    }
}

@Composable
@Preview
fun HomeFriendContainerPreview() {
    NOWSOPTAndroidTheme {
        Column {
            HomeFriendContainer(
                containerHeight = 80.dp,
                title = "우상욱",
                subTitle = "놀기"
            )
        }
    }
}