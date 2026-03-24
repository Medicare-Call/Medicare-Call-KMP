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
import com.konkuk.medicarecall.resources.ic_check
import com.konkuk.medicarecall.resources.ic_report_normal
import com.konkuk.medicarecall.ui.feature.home.viewmodel.MedicineUiState
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun CareCallSummary(
    modifier: Modifier = Modifier,
    meal: String,
    sleep: String,
    statusIcon: Painter? = null,
    tagIcon: Painter? = null,
    medicines: List<MedicineUiState>,
    isTaken: Boolean,
) {
    val resolvedStatusIcon = statusIcon ?: painterResource(Res.drawable.ic_report_normal)
    val resolvedTagIcon = tagIcon ?: painterResource(Res.drawable.ic_check)

    Column(modifier = modifier.fillMaxWidth()) {
        // 케어콜 요약
        Row(
            modifier = modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(start = 16.dp, top = 10.dp, bottom = 10.dp),
        ) {
            Text(
                text = "케어콜 요약",
                style = MediCareCallTheme.typography.R_14,
                color = MediCareCallTheme.colors.gray4,
            )
        }
        // 1. 식사
        Column(
            modifier = modifier
                .background(Color.White)
                .padding(16.dp, 14.dp),
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "식사",
                    style = MediCareCallTheme.typography.R_14,
                    color = MediCareCallTheme.colors.gray6,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = modifier,
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
                    text = meal,
                    style = MediCareCallTheme.typography.M_16,
                    color = MediCareCallTheme.colors.black,
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        // 2. 복약
        Column(
            modifier = modifier
                .background(Color.White)
                .padding(16.dp, 14.dp),
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "복약",
                    style = MediCareCallTheme.typography.R_14,
                    color = MediCareCallTheme.colors.gray6,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            medicines.forEachIndexed { index, medicine ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        // 복약 아이콘 (먹음/안먹음/미기록)
                        Icon(
                            painter = resolvedTagIcon, // 임시
                            contentDescription = null,
                            modifier = Modifier.height(24.dp),
                            tint = Color.Unspecified,
                        )

                        Spacer(modifier = Modifier.width(8.dp))
                        // 복약 이름
                        Text(
                            text = medicine.medicineName,
                            style = MediCareCallTheme.typography.M_16,
                            color = MediCareCallTheme.colors.black,
                        )
                    }
                }
                if (index != medicines.lastIndex) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        // 3. 수면
        Column(
            modifier = modifier
                .background(Color.White)
                .padding(16.dp, 14.dp),
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "수면",
                    style = MediCareCallTheme.typography.R_14,
                    color = MediCareCallTheme.colors.gray6,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
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
                    text = sleep,
                    style = MediCareCallTheme.typography.M_16,
                    color = MediCareCallTheme.colors.black,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewCareCallSummary() {
    MediCareCallTheme {
        val mockMedicines = listOf(
            MedicineUiState(
                medicineName = "혈압약",
                todayTakenCount = 1,
                todayRequiredCount = 2,
                nextDoseTime = "저녁 8:00",
            ),
            MedicineUiState(
                medicineName = "비타민",
                todayTakenCount = 0,
                todayRequiredCount = 1,
                nextDoseTime = "점심 12:00",
            ),
        )

        CareCallSummary(
            meal = "된장찌개에 밥",
            medicines = mockMedicines,
            isTaken = true,
            sleep = "충분히 수면",
            statusIcon = painterResource(Res.drawable.ic_report_normal),
            tagIcon = painterResource(Res.drawable.ic_check),
        )
    }
}
