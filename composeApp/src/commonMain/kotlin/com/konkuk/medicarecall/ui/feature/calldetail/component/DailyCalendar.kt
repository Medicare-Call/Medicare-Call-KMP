package com.konkuk.medicarecall.ui.feature.calldetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.ic_leftarrow_date
import com.konkuk.medicarecall.resources.ic_report_normal
import com.konkuk.medicarecall.resources.ic_rightarrow_date
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import kotlinx.datetime.LocalDate
import kotlinx.datetime.number
import org.jetbrains.compose.resources.painterResource

@Composable
fun DailyCalendar(
    modifier: Modifier = Modifier,
    date: LocalDate,
    day: String,
    onBack: () -> Unit,
    onNext: () -> Unit = {},
    isBackEnabled: Boolean = true,
    isNextEnabled: Boolean = true,
    enabledIconTint: Color = MediCareCallTheme.colors.gray6,
    disabledIconTint: Color = MediCareCallTheme.colors.gray1,
    titleTextStyle: TextStyle = MediCareCallTheme.typography.M_16,
    titleColor: Color = MediCareCallTheme.colors.gray6,
    backIcon: Painter = painterResource(Res.drawable.ic_leftarrow_date),
    nextIcon: Painter = painterResource(Res.drawable.ic_rightarrow_date),
) {
    val title = if (day.isBlank()) {
        "${date.month.number}월 ${date.day}일"
    } else {
        "${date.month.number}월 ${date.day}일 ($day)"
    }
    val backIconTint = if (isBackEnabled) enabledIconTint else disabledIconTint
    val nextIconTint = if (isNextEnabled) enabledIconTint else disabledIconTint

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier
                    .size(22.dp)
                    .clickable(enabled = isBackEnabled) { onBack() },
                painter = backIcon,
                contentDescription = "leftArrow back",
                tint = backIconTint,
            )

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = title,
                    style = titleTextStyle,
                    color = titleColor,
                )
            }

            Icon(
                modifier = Modifier
                    .size(22.dp)
                    .clickable(enabled = isNextEnabled) { onNext() },
                painter = nextIcon,
                contentDescription = "rightArrow next",
                tint = nextIconTint,
            )
        }
        HorizontalDivider(color = MediCareCallTheme.colors.gray1, thickness = 1.dp)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDailyCalendar() {
    MediCareCallTheme {
        DailyCalendar(
            modifier = Modifier.padding(20.dp),
            date = LocalDate(2025, 5, 19),
            day = "오늘",
            onBack = {},
            backIcon = painterResource(Res.drawable.ic_leftarrow_date),
            nextIcon = painterResource(Res.drawable.ic_rightarrow_date),
        )
    }
}
