package com.konkuk.medicarecall.ui.feature.login.calltime.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.ui.common.component.WheelPicker
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme

@Composable
fun FirstTimeWheelPicker(
    modifier: Modifier = Modifier,
    initialHour: Int = 9,
    initialMinute: Int = 0,
    onTimeChange: (hour: Int, minute: Int) -> Unit = { _, _ -> },
) {
    var hour by remember { mutableIntStateOf(initialHour) }
    var minute by remember { mutableIntStateOf(initialMinute) }

    val hourItems = (1..12).map { it.toString() }
    val minuteItems = listOf("00", "10", "20", "30", "40", "50")

    val mainColor = MediCareCallTheme.colors.main
    val grayColor = MediCareCallTheme.colors.gray3
    val textStyle = MediCareCallTheme.typography.M_20

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // AM label (fixed)
            WheelPicker(
                items = listOf("오전"),
                selectedIndex = 0,
                onSelectedIndexChange = {},
                modifier = Modifier.width(60.dp),
                textStyle = textStyle,
                selectedTextColor = mainColor,
                unselectedTextColor = grayColor,
            )
            Spacer(Modifier.width(32.dp))

            // Hour picker (1-12)
            WheelPicker(
                items = hourItems,
                selectedIndex = (initialHour - 1).coerceIn(0, 11),
                onSelectedIndexChange = { index ->
                    hour = index + 1
                    onTimeChange(hour, minute)
                },
                modifier = Modifier.width(48.dp),
                textStyle = textStyle,
                selectedTextColor = mainColor,
                unselectedTextColor = grayColor,
            )
            Spacer(modifier = Modifier.width(18.dp))
            Text(
                ":",
                style = MediCareCallTheme.typography.M_20,
                color = MediCareCallTheme.colors.main,
            )
            Spacer(modifier = Modifier.width(18.dp))

            // Minute picker (00, 10, 20, 30, 40, 50)
            WheelPicker(
                items = minuteItems,
                selectedIndex = (initialMinute / 10).coerceIn(0, 5),
                onSelectedIndexChange = { index ->
                    minute = index * 10
                    onTimeChange(hour, minute)
                },
                modifier = Modifier.width(48.dp),
                textStyle = textStyle,
                selectedTextColor = mainColor,
                unselectedTextColor = grayColor,
            )
        }
    }
}
