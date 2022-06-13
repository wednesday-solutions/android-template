package com.wednesday.template.presentation.base.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.wednesday.template.presentation.base.extensions.asString

@Composable
fun DialogHost(
    hostState: DialogHostState,
    onDismiss: () -> Unit = hostState::dismissDialog
) {
    val dialogData = hostState.currentDialogData

    dialogData?.apply {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = title.asString())
            },
            text = {
                Text(text = text.asString())
            },
            icon = icon?.let {
                {
                    Icon(imageVector = it, contentDescription = iconContentDescription)
                }
            },
            confirmButton = {
                TextButton(
                    onClick = { onConfirmButtonClicked(hostState) }
                ) {
                    Text(text = confirmButtonText.asString())
                }
            },
            dismissButton = dismissButtonText?.let {
                {
                    TextButton(onClick = { onDismissButtonClicked(hostState) }) {
                        Text(text = it.asString())
                    }
                }
            }
        )
    }
}
