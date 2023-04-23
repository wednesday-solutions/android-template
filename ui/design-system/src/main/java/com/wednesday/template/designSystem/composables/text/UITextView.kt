package com.wednesday.template.designSystem.composables.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun UITextView(
    text: String,
    textType: UITextType,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
) {
    // TODO: Implement custom text styles
    Text(
        text,
        maxLines = maxLines,
        style = textType.typography,
        modifier = modifier,
        overflow = overflow,
        color = color,
        textAlign = textAlign,
        textDecoration = textDecoration,
    )
}
