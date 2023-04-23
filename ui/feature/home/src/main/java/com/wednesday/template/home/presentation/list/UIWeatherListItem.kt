package com.wednesday.template.home.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wednesday.template.designSystem.composables.card.UICard
import com.wednesday.template.designSystem.composables.text.UITextType
import com.wednesday.template.designSystem.composables.text.UITextView
import com.wednesday.template.feature.core.extensions.asString
import com.wednesday.template.home.R
import com.wednesday.template.presentation.weather.UIWeather

@Composable
fun UIWeatherListItem(
    modifier: Modifier,
    item: UIWeather,
) {
    UICard(modifier = modifier) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(Modifier.weight(3f)) {
                    UITextView(
                        text = item.title.asString(),
                        textType = UITextType.Large.Regular,
                    )
                    Spacer(Modifier.height(8.dp))
                    UITextView(
                        text = item.currentTemp.asString(),
                        textType = UITextType.Large.Bold,
                    )
                }
                AsyncImage(
                    modifier = Modifier.weight(2f),
                    model = item.iconUrl,
                    contentDescription = stringResource(id = R.string.acc_weather_icon),
                )
            }
            Spacer(Modifier.height(8.dp))
            UITextView(text = item.description.asString(), textType = UITextType.Medium.Bold)
            Spacer(Modifier.height(4.dp))
            UITextView(text = item.feelsLike.asString(), textType = UITextType.Small.Regular)
            Spacer(Modifier.height(4.dp))
            UITextView(text = item.minMaxTemp.asString(), textType = UITextType.Small.Regular)
            Spacer(Modifier.height(8.dp))
        }
    }
}
