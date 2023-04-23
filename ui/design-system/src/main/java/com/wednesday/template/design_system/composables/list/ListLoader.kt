package com.wednesday.template.design_system.composables.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun ListLoader() {
    Column(Modifier.fillMaxWidth()) {
        val colorScheme = MaterialTheme.colorScheme
        val color = remember (colorScheme) {
            colorScheme.surfaceColorAtElevation(3.dp)
        }
        val highlightColor = remember(colorScheme) {
            colorScheme.surfaceVariant
        }
        for (index in 1..3) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .placeholder(
                        visible = true,
                        color = color,
                        shape = RoundedCornerShape(4.dp),
                        highlight = PlaceholderHighlight.shimmer(
                            highlightColor = highlightColor
                        ),
                    )
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}