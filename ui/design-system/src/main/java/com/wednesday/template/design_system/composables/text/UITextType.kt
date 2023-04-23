package com.wednesday.template.design_system.composables.text

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

sealed class UITextType(
    val fontWeight: FontWeight,
    val lineHeight: TextUnit,
    val fontSize: TextUnit,
    val letterSpacing: TextUnit,
) {
    val typography
        get() = TextStyle(
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            ),
            fontSize = fontSize,
            fontWeight = fontWeight,
            lineHeight = lineHeight,
            letterSpacing = letterSpacing
        )

    object Small {
        object Regular : UITextType(
            fontWeight = FontWeight.SemiBold,
            lineHeight = 20.sp,
            fontSize = 14.sp,
            letterSpacing = 0.sp,
        )

        object Bold : UITextType(
            fontWeight = FontWeight.Bold,
            lineHeight = 20.sp,
            fontSize = 14.sp,
            letterSpacing = 0.sp,
        )
    }

    object Medium {
        object Regular : UITextType(
            fontWeight = FontWeight.Medium,
            lineHeight = 24.sp,
            fontSize = 20.sp,
            letterSpacing = 0.sp,
        )

        object Bold : UITextType(
            fontWeight = FontWeight.Normal,
            lineHeight = 24.sp,
            fontSize = 20.sp,
            letterSpacing = 0.sp,
        )
    }

    object Large {
        object Regular : UITextType(
            fontWeight = FontWeight.SemiBold,
            lineHeight = 28.sp,
            fontSize = 24.sp,
            letterSpacing = 0.sp,
        )

        object Bold : UITextType(
            fontWeight = FontWeight.Bold,
            lineHeight = 28.sp,
            fontSize = 24.sp,
            letterSpacing = 0.sp,
        )
    }
}