package org.sopt.designsystem.component.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.designsystem.ui.theme.NOWSOPTAndroidTheme

@Composable
fun RegularTextField(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    value: String = "",
    title: String = "",
    onValueChange: (String) -> Unit = { _ -> },
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    maxLines: Int = 1,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        singleLine = maxLines == 1,
        minLines = minLines,
        ) { innerText ->
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(
                        min = 27.dp,
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if(!title.isBlank()){
                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            modifier = Modifier.width(60.dp),
                            text = title + " : ",
                            color = Color.Black,
                            style = textStyle,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Column {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        innerText()
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                color = Color.Gray,
                                style = textStyle,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    HorizontalDivider(
                        thickness = 2.dp,
                        color = Color.DarkGray,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun RegularTextFieldPreview() {
    NOWSOPTAndroidTheme {
        Column {
            RegularTextField(title = "PW", value = "", placeholder = "비밀번호 입력하세요")
        }
    }
}