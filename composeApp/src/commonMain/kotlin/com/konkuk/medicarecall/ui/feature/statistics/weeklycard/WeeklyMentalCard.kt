package com.konkuk.medicarecall.ui.feature.statistics.weeklycard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.*
import com.konkuk.medicarecall.resources.ic_emoji_bad
import com.konkuk.medicarecall.resources.ic_emoji_good
import com.konkuk.medicarecall.resources.ic_emoji_normal
import com.konkuk.medicarecall.ui.feature.statistics.viewmodel.WeeklySummaryUiState
import com.konkuk.medicarecall.ui.theme.LocalMediCareCallShadowProvider
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import com.konkuk.medicarecall.ui.theme.figmaShadow
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun WeeklyMentalCard(
    modifier: Modifier = Modifier,
    mental: WeeklySummaryUiState.WeeklyMentalUiState,
) {
    Card(
        modifier = modifier
            .figmaShadow(
                group = LocalMediCareCallShadowProvider.current.shadow01,
                cornerRadius = 14.dp,
            ),

        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(14.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            // 1) Title: 심리 상태
            Text(
                "심리상태",
                style = MediCareCallTheme.typography.R_15,
                color = MediCareCallTheme.colors.gray5,
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 2) 좋음+보통+나쁨
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                MentalStatusRow(
                    label = "좋음",
                    iconResId = Res.drawable.ic_emoji_good,
                    count = mental.good,
                )
                MentalStatusRow(
                    label = "보통",
                    iconResId = Res.drawable.ic_emoji_normal,
                    count = mental.normal,
                )
                MentalStatusRow(
                    label = "나쁨",
                    iconResId = Res.drawable.ic_emoji_bad,
                    count = mental.bad,
                )
            }
        }
    }
}

@Composable
private fun MentalStatusRow(
    label: String,
    iconResId: DrawableResource,
    count: Int,
) {
    val isUnrecorded = count <= 0
    val countText = if (isUnrecorded) "-" else count.toString()
    val textColor =
        if (isUnrecorded) MediCareCallTheme.colors.gray4 else MediCareCallTheme.colors.gray6

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = label,
            style = MediCareCallTheme.typography.R_15,
            color = MediCareCallTheme.colors.gray8,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Icon(
            modifier = Modifier.size(14.dp),
            painter = painterResource(iconResId),
            contentDescription = label,
            tint = Color.Unspecified,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "${countText}번",
            style = MediCareCallTheme.typography.R_14,
            color = textColor,
            modifier = Modifier.width(22.dp),
            textAlign = TextAlign.End,
        )
    }
}

@Composable
fun PreviewWeeklyMentalCardRecorded() {
    WeeklyMentalCard(
        modifier = Modifier.size(155.dp),
        mental = WeeklySummaryUiState.WeeklyMentalUiState(
            good = 4,
            normal = 2,
            bad = 1,
        ),
    )
}

@Composable
fun PreviewWeeklyMentalCardUnrecorded() {
    WeeklyMentalCard(
        modifier = Modifier.size(155.dp),
        mental = WeeklySummaryUiState.WeeklyMentalUiState.EMPTY,
    )
}
