package org.sopt.designsystem.component.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SoptAlertDialog(
    title: String,
    positiveLabel: String,
    negativeLabel: String,
    onClickPositive: () -> Unit,
    onClickNegative: () -> Unit,
){
    AlertDialog(
        onDismissRequest = { onClickNegative() },
        title = { Text(text = title, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center) },
        dismissButton = {
            TextButton(onClick = { onClickNegative() }) {
                Text(text = negativeLabel, color = Color.Black)
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onClickPositive()
                }) {
                Text(text = positiveLabel, color = Color.Black)
            }
        }
    )
}