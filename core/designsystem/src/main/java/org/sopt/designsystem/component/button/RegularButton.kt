package org.sopt.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.designsystem.ui.theme.NOWSOPTAndroidTheme

@Composable
fun RegularButton(
    modifier: Modifier = Modifier,
    text: String = "",
    clickable: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClick,
        colors = ButtonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black,
            disabledContainerColor = Color.DarkGray,
            disabledContentColor = Color.LightGray
        ),
        enabled = clickable
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}

@Preview
@Composable
fun RegularButtonPreview() {
    NOWSOPTAndroidTheme {
        Column {
            RegularButton(onClick = {}, text = "버튼")
        }
    }
}