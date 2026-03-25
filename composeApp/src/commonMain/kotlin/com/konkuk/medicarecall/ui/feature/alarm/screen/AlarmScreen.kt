package com.konkuk.medicarecall.ui.feature.alarm.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.medicarecall.domain.model.type.AlarmType
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.ic_settings_back
import com.konkuk.medicarecall.ui.feature.alarm.component.AlarmItem
import com.konkuk.medicarecall.ui.feature.alarm.viewmodel.AlarmViewModel
import com.konkuk.medicarecall.ui.feature.settings.component.SettingsTopAppBar
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AlarmScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AlarmViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MediCareCallTheme.colors.bg)
            .statusBarsPadding(),
    ) {
        SettingsTopAppBar(
            title = "알림",
            leftIcon = {
                Icon(
                    painterResource(Res.drawable.ic_settings_back),
                    contentDescription = "setting back",
                    modifier = modifier
                        .size(24.dp)
                        .clickable { onBack() },
                    tint = MediCareCallTheme.colors.black,
                )
            },
        )
        Column(
            modifier = modifier.verticalScroll(scrollState),
        ) {
            if (uiState.errorMessage != null) {
                AlarmItem(
                    AlarmType.READ_ALARM,
                    "공지사항 오류 발생",
                    uiState.errorMessage ?: "",
                )
            } else {
                uiState.alarmPages.forEach { alarmPage ->
                    alarmPage.notifications.forEach { alarm ->
                        AlarmItem(
                            alarmType = if (alarm.isRead) AlarmType.READ_ALARM else AlarmType.NEW_ALARM,
                            content = alarm.body,
                            date = alarm.createdAt.replace("-", "."),
                        )
                    }
                }

            }
        }
//        AlarmItem(
//            AlarmType.NEW_ALARM,
//            "✅ 1차 케어콜이 완료되었어요. 확인해 보세요!",
//            "7월 8일 13:15",
//        )
//        AlarmItem(
//            AlarmType.READ_ALARM,
//            "❗ 박막례 어르신 건강이상 징후가 탐지되었어요. 확인해 주세요!",
//            "7월 7일 13:15",
//        )
//        AlarmItem(
//            AlarmType.READ_ALARM,
//            "📞 김옥자 어르신 케어콜 부재중 상태입니다. 확인해 주세요!",
//            "7월 7일 13:15",
//        )
//        AlarmItem(
//            AlarmType.READ_ALARM,
//            "❗ 박막례 어르신 건강이상 징후가 탐지되었어요. 확인해 주세요!",
//            "7월 7일 13:15",
//        )
//        AlarmItem(
//            AlarmType.READ_ALARM,
//            "✅ 1차 케어콜이 완료되었어요. 확인해 보세요!",
//            "7월 7일 13:15",
//        )
    }
}

@Composable
private fun AlarmScreenPreview() {
    MediCareCallTheme {
        AlarmScreen(
            onBack = {},
        )
    }
}
