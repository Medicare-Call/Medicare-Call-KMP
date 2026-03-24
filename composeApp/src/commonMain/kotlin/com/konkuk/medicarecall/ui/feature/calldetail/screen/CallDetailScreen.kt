package com.konkuk.medicarecall.ui.feature.calldetail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.ic_check
import com.konkuk.medicarecall.resources.ic_report_attention
import com.konkuk.medicarecall.resources.ic_report_normal
import com.konkuk.medicarecall.ui.common.component.TopAppBar
import com.konkuk.medicarecall.ui.feature.calldetail.component.AudioPlayerCard
import com.konkuk.medicarecall.ui.feature.calldetail.component.CareCallSummary
import com.konkuk.medicarecall.ui.feature.calldetail.component.DailyCalendar
import com.konkuk.medicarecall.ui.feature.calldetail.component.MealSegmentedControl
import com.konkuk.medicarecall.ui.feature.calldetail.component.SpecialNoteSection
import com.konkuk.medicarecall.ui.feature.home.viewmodel.MedicineUiState
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.painterResource

@Composable
fun CallDetailScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MediCareCallTheme.colors.bg),
    ) {
        TopAppBar(
            title = "하루 요약 상세",
            onBack = onBack,
            titleTextStyle = MediCareCallTheme.typography.B_17,
            titleColor = MediCareCallTheme.colors.gray10,
        )
        DailyCalendar(
            date = LocalDate(2026, 3, 17),
            day = "오늘",
            onBack = {
                onBack()
            },
        )
        var selectedMeal by remember { mutableStateOf("아침") }

        MealSegmentedControl(
            selectedItem = selectedMeal,
            onItemClick = { selectedMeal = it },
        )

        AudioPlayerCard()
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
        Spacer(modifier=modifier.height(12.dp))
        Column {
            CareCallSummary(
                meal = "된장찌개에 밥",
                medicines = mockMedicines,
                isTaken = true,
                sleep = "충분히 수면",
                statusIcon = painterResource(Res.drawable.ic_report_normal),
                tagIcon = painterResource(Res.drawable.ic_check),
            )
            Spacer(modifier=modifier.height(6.dp))
            if (selectedMeal == "저녁") {
                SpecialNoteSection(
                    note = "무릎통증",
                    description = "어제부터 있던 무릎통증이 있어요",
                    statusIcon = painterResource(Res.drawable.ic_report_attention),
                )
            }
        }

    }

}

@Preview
@Composable
private fun CallDetailScreenPreview() {
    CallDetailScreen() {

    }

}
