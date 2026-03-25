package com.konkuk.medicarecall.ui.feature.calldetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.ic_report_attention
import com.konkuk.medicarecall.resources.ic_report_normal
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun SpecialNoteSection(
    modifier: Modifier = Modifier,
    note: String,
    description: String,
    statusIcon: Painter? = null,
) {
    val resolvedStatusIcon = statusIcon ?: painterResource(Res.drawable.ic_report_attention)
    Column(
        modifier = modifier
            .background(Color.White)
            .padding(16.dp, 14.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = "특이사항",
                style = MediCareCallTheme.typography.R_14,
                color = MediCareCallTheme.colors.gray6,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = resolvedStatusIcon,
                contentDescription = null, // 임시 아이콘
                modifier = Modifier.height(24.dp),
                tint = Color.Unspecified,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = note,
                style = MediCareCallTheme.typography.SB_16,
                color = MediCareCallTheme.colors.black,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            style = MediCareCallTheme.typography.R_15,
            color = MediCareCallTheme.colors.gray6,
        )
    }
}

@Preview
@Composable
private fun SpecialNoteSectionPreview() {
    SpecialNoteSection(
        note = "무릎통증",
        description = "어제부터 있던 무릎통증이 있어요",
        statusIcon = painterResource(Res.drawable.ic_report_attention)
        )

}
