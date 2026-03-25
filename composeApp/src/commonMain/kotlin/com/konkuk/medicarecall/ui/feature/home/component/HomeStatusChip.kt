package com.konkuk.medicarecall.ui.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.domain.model.type.HomeStatusType
import com.konkuk.medicarecall.ui.theme.G50
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import com.konkuk.medicarecall.ui.theme.Negative
import com.konkuk.medicarecall.ui.theme.Warning2
import com.konkuk.medicarecall.ui.theme.gray3
import com.konkuk.medicarecall.ui.theme.main

@Composable
fun HomeStatusChip(
    statusType: HomeStatusType,
    modifier: Modifier = Modifier,
) {
    val containerColor: Color
    val textColor: Color

    when (statusType) {
        HomeStatusType.GOOD -> {
            containerColor = G50
            textColor = main
        }

        HomeStatusType.ATTENTION -> {
            containerColor = Color(0xffFFA13d).copy(alpha = 0.1f)
            textColor = Warning2
        }

        HomeStatusType.WARNING -> {
            containerColor = Negative.copy(alpha = 0.1f)
            textColor = Negative
        }

        HomeStatusType.UNRECORDED -> {
            containerColor = Color(0xffF0F0F0)
            textColor = gray3
        }
    }

    Box(Modifier.background(containerColor, RoundedCornerShape(20.dp))) {
        Text(statusType.title, color = textColor, style = MediCareCallTheme.typography.SB_12, modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp))
    }
}
