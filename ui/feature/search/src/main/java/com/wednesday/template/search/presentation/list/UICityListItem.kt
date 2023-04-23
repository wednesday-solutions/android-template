package com.wednesday.template.search.presentation.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.wednesday.template.design_system.composables.text.UITextType
import com.wednesday.template.design_system.composables.text.UITextView
import com.wednesday.template.feature.core.extensions.asString
import com.wednesday.template.presentation.weather.UICity
import com.wednesday.template.search.R

@Composable
fun UICityListItem(modifier: Modifier, item: UICity, onFavouriteClick: (UICity) -> Unit) {
    ListItem(
        modifier = modifier,
        headlineText = {
            UITextView(
                text = item.displayTitle.asString(),
                textType = UITextType.Medium.Bold
            )
        },
        supportingText = {
            UITextView(
                text = item.latitude,
                textType = UITextType.Small.Regular
            )
        },
        trailingContent = {
            IconButton(onClick = { onFavouriteClick(item) }) {
                val icon = remember(item.isFavourite) {
                    if (item.isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder
                }
                val description = remember(item.isFavourite) {
                    if (item.isFavourite) R.string.acc_unmark_fav else R.string.acc_mark_fav
                }
                Icon(
                    icon,
                    contentDescription = stringResource(id = description, item.title)
                )
            }
        }
    )
}