package com.konkuk.medicarecall.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.domain.model.Medication
import com.konkuk.medicarecall.domain.model.type.MedicationCategory
import com.konkuk.medicarecall.domain.model.type.MedicationTime
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme

@Composable
private fun MedicationTimeSection(
    title: String,
    medications: List<String>,
    onRemoveChip: (String) -> Unit,
) {
    if (medications.isNotEmpty()) {
        Column {
            Text(
                text = title,
                color = MediCareCallTheme.colors.gray5,
                style = MediCareCallTheme.typography.R_15,
            )
            Spacer(Modifier.height(10.dp))
            val scrollState = rememberScrollState()
            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .horizontalScroll(scrollState),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                medications.toList().forEach { medication ->
                    ChipItem(medication) {
                        onRemoveChip(medication)
                    }
                }
            }
        }
    }
}

@Composable
private fun MedicationCategorySelector(
    selectedCategory: MedicationCategory,
    onSelectCategory: (MedicationCategory) -> Unit,
) {
    Column {
        Text(
            text = "약 종류를 선택해 주세요.",
            color = MediCareCallTheme.colors.gray6,
            style = MediCareCallTheme.typography.R_14,
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MedicationRadioButton(
                text = "처방약",
                selected = selectedCategory == MedicationCategory.PRESCRIPTION,
                onClick = { onSelectCategory(MedicationCategory.PRESCRIPTION) },
            )

            MedicationRadioButton(
                text = "영양제",
                selected = selectedCategory == MedicationCategory.SUPPLEMENT,
                onClick = { onSelectCategory(MedicationCategory.SUPPLEMENT) },
            )
        }
    }
}

@Composable
private fun MedicationRadioButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .border(
                    width = 1.2.dp,
                    color = if (selected) MediCareCallTheme.colors.main else MediCareCallTheme.colors.gray3,
                    shape = CircleShape,
                ),
            contentAlignment = Alignment.Center,
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(MediCareCallTheme.colors.main),
                )
            }
        }

        Spacer(modifier = Modifier.padding(start = 8.dp))

        Text(
            text = text,
            color = MediCareCallTheme.colors.gray6,
            style = MediCareCallTheme.typography.R_14,
        )
    }
}

@Composable
fun MedicationItem(
    medications: List<Medication>,
    selectedTimes: List<MedicationTime>,
    selectedCategory: MedicationCategory,
    inputTextState: TextFieldState,
    onSelectTime: (MedicationTime) -> Unit,
    onSelectCategory: (MedicationCategory) -> Unit,
    onAddMedication: (String) -> Unit,
    onRemoveMedication: (Medication) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            "복약 정보",
            color = MediCareCallTheme.colors.gray7,
            style = MediCareCallTheme.typography.M_17,
        )
        Spacer(Modifier.height(10.dp))

        MedicationTime.entries.forEach { timeType ->
            val medsForTime = medications.filter { timeType in it.times }
            if (medsForTime.isNotEmpty()) {
                Spacer(Modifier.height(10.dp))
                MedicationTimeSection(
                    title = timeType.displayName,
                    medications = medsForTime.map { it.medicine },
                    onRemoveChip = { medicineName ->
                        medications.find { it.medicine == medicineName }?.let { onRemoveMedication(it) }
                    },
                )
            }
        }

        if (medications.isNotEmpty()) {
            Spacer(Modifier.height(20.dp))
        }

        Text(
            text = "복약 정보 입력",
            color = MediCareCallTheme.colors.gray7,
            style = MediCareCallTheme.typography.M_17,
        )
        Spacer(Modifier.height(12.dp))

        MedicationCategorySelector(
            selectedCategory = selectedCategory,
            onSelectCategory = onSelectCategory,
        )

        Spacer(Modifier.height(20.dp))

        Text(
            text = "약 이름을 입력해주세요",
            color = MediCareCallTheme.colors.gray6,
            style = MediCareCallTheme.typography.R_14,
        )
        Spacer(Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            MedicationTime.entries.forEach { time ->
                Box(
                    Modifier
                        .clip(CircleShape)
                        .background(
                            color = if (time in selectedTimes) MediCareCallTheme.colors.main
                            else MediCareCallTheme.colors.white,
                        )
                        .border(
                            1.2.dp,
                            if (time in selectedTimes) MediCareCallTheme.colors.main
                            else MediCareCallTheme.colors.gray2,
                            CircleShape,
                        )
                        .clickable(
                            indication = null,
                            interactionSource = null,
                            onClick = { onSelectTime(time) },
                        ),
                ) {
                    Text(
                        time.displayName,
                        color = if (time in selectedTimes) MediCareCallTheme.colors.g50
                        else MediCareCallTheme.colors.gray5,
                        style = if (time in selectedTimes) MediCareCallTheme.typography.SB_14
                        else MediCareCallTheme.typography.R_14,
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp),
                    )
                }
            }
        }
        Spacer(Modifier.height(20.dp))
        AddTextField(
            textFieldState = inputTextState,
            placeHolder = "예시) 당뇨약",
            clickPlus = {
                val inputText = inputTextState.text.toString()
                if (inputText.isNotBlank() && selectedTimes.isNotEmpty()) {
                    onAddMedication(inputText)
                    inputTextState.edit { replace(0, length, "") }
                }
            },
        )
    }
}

@Composable
private fun MedicationItemPreview() {
    MediCareCallTheme {
        Column(Modifier.padding(16.dp)) {
            MedicationItem(
                medications = listOf(
                    Medication("당뇨약", listOf(MedicationTime.MORNING)),
                    Medication("혈압약", listOf(MedicationTime.DINNER)),
                ),
                selectedTimes = listOf(),
                inputTextState = TextFieldState(""),
                onSelectTime = {},
                onAddMedication = {},
                onRemoveMedication = {},
                onSelectCategory = {},
                selectedCategory =  MedicationCategory.PRESCRIPTION,
            )
        }
    }
}
