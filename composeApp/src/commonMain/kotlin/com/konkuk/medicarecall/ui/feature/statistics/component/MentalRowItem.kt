package com.konkuk.medicarecall.ui.feature.statistics.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.*
import com.konkuk.medicarecall.resources.ic_emoji_good
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun MentalRowItem(
    label: String,
    count: Int,
    iconRes: DrawableResource,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier,
    ) {
        Text(
            text = label,
            style = MediCareCallTheme.typography.R_14,
            color = MediCareCallTheme.colors.gray6,
        )
        Spacer(modifier = Modifier.width(10.dp))

        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = Color.Unspecified,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "${count}번",
            style = MediCareCallTheme.typography.R_14,
            color = MediCareCallTheme.colors.gray6,
        )
    }
}

@Composable
fun PreviewMentalRowItem() {
    MentalRowItem(
        label = "좋음",
        count = 4,
        iconRes = Res.drawable.ic_emoji_good,
    )
}
