package org.sopt.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.sopt.designsystem.ui.theme.NOWSOPTAndroidTheme

@Composable
fun ReqresContainer(
    email: String? = "",
    containerHeight: Dp,
    firstName: String? = "",
    lastName: String? = "",
    imageUri: String? = "",
    id: Int? = 0,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context)
        .data(imageUri)
        .memoryCacheKey(imageUri)
        .diskCacheKey(imageUri)
        .build()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(
                if (id == 0) 100.dp else containerHeight
            )
            .background(Color.White)
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = imageRequest,
            contentDescription = "Profile Image",
            modifier = modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight,
        )
        Text(
            modifier = modifier
                .padding(start = 20.dp),
            text = "$firstName $lastName",
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "$email",
            fontSize = 10.sp
        )
    }
}

@Composable
@Preview
fun ReqresContainerPreview() {
    NOWSOPTAndroidTheme {
        Column {
            ReqresContainer(
                email = "michael.lawson@reqres.in",
                firstName = "john",
                lastName = "hi",
                imageUri = "https://reqres.in/img/faces/7-image.jpg",
                id = 1,
                containerHeight = 80.dp
            )
        }
    }
}