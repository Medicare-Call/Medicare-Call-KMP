package com.konkuk.medicarecall.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.konkuk.medicarecall.resources.ic_arrow_down_big
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun NameBar(
    name: String,
    notificationCount: Int,
    modifier: Modifier = Modifier,
    navigateToAlarm: () -> Unit = {},
    onDropdownClick: () -> Unit,
) {
    Box(modifier = modifier.background(Color.White)) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = null,
                ) { onDropdownClick() },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = name,
                    style = MediCareCallTheme.typography.SB_24,
                    color = MediCareCallTheme.colors.black,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(Res.drawable.ic_arrow_down_big),
                    contentDescription = "arrow down",
                    tint = MediCareCallTheme.colors.gray3,
                )
            }

            NotificationIconWithBadge(
                notificationCount = notificationCount,
                onClick = navigateToAlarm,
            )
        }
    }
}

@Composable
fun PreviewNameBar() {
    MediCareCallTheme {
        NameBar(
            name = "김옥자",
            notificationCount = 4,
            onDropdownClick = {},
        )
    }
}
