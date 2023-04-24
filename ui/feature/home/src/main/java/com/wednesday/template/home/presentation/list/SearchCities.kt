package com.wednesday.template.home.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wednesday.template.data.core.UnitCallback
import com.wednesday.template.designSystem.composables.text.UITextType
import com.wednesday.template.designSystem.composables.text.UITextView
import com.wednesday.template.feature.core.extensions.asString
import com.wednesday.template.home.R
import com.wednesday.template.presentation.weather.UISearchCitiesPlaceholder

@Composable
fun SearchCities(
    modifier: Modifier,
    item: UISearchCitiesPlaceholder,
    onSearchClick: UnitCallback,
) {
    Column(
        modifier.then(
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        UITextView(text = item.text.asString(), textType = UITextType.Medium.Regular)
        Spacer(Modifier.height(16.dp))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(0.75f),
            onClick = onSearchClick,
        ) {
            UITextView(
                text = stringResource(id = R.string.search),
                textType = UITextType.Small.Regular,
            )
        }
    }
}
