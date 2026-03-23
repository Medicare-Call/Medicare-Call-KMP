package com.konkuk.medicarecall.ui.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.domain.util.now
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.ic_arrow_big_back
import com.konkuk.medicarecall.resources.ic_arrow_big_forward
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import com.konkuk.medicarecall.ui.theme.White
import com.konkuk.medicarecall.ui.theme.gray6
import kotlinx.datetime.LocalDate
import kotlinx.datetime.number
import org.jetbrains.compose.resources.vectorResource

@Composable
fun DateBar(
    day: LocalDate,
    onBack: () -> Unit = {},
    onNext: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val todayString by remember { if (day == LocalDate.now()) mutableStateOf("(오늘)") else mutableStateOf("") }

    Box(
        Modifier
            .background(White)
            .border(0.5.dp, MediCareCallTheme.colors.gray1),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp, vertical = 16.dp,
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                imageVector = vectorResource(Res.drawable.ic_arrow_big_back),
                contentDescription = "하루 전으로",
                tint = MediCareCallTheme.colors.gray6,
                modifier = Modifier.clickable(onClick = onBack),
            )
            Text(
                "${day.month.number}월 ${day.day}일 $todayString",
                color = gray6,
                style = MediCareCallTheme.typography.M_16,
            )
            Icon(
                imageVector = vectorResource(Res.drawable.ic_arrow_big_forward),
                contentDescription = "하루 뒤로",
                tint = if (day == LocalDate.now()) MediCareCallTheme.colors.gray2 else MediCareCallTheme.colors.gray6,
                modifier = Modifier.clickable(enabled = day != LocalDate.now(), onClick = onNext),
            )
        }
    }
}
