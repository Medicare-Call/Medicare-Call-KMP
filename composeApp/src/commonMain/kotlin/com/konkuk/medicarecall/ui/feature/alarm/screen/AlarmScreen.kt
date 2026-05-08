package com.konkuk.medicarecall.ui.feature.alarm.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.domain.model.type.AlarmType
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.ic_arrow_big_back
import com.konkuk.medicarecall.ui.feature.alarm.component.AlarmItem
import com.konkuk.medicarecall.ui.feature.settings.component.SettingsTopAppBar
import com.konkuk.medicarecall.ui.model.AlarmModel
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import com.konkuk.medicarecall.ui.type.AlarmActionType
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AlarmScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current
    val alarmList = remember {
        listOf(
            AlarmModel(
                1, AlarmType.NEW_ALARM, "🚨 엄마의 케어콜이 연속 2회 부재중 상태입니다. 확인해 주세요!", "10분 전", AlarmActionType.CALL_ACTIVE,
            ),
            AlarmModel(
                2, AlarmType.NEW_ALARM, "💊🔔 복약 상태가 관심입니다. 확인해주세요.", "2시간 전", AlarmActionType.NONE,
            ),
            AlarmModel(
                3, AlarmType.READ_ALARM, "🛌🚨 수면 상태가 경고입니다. 확인해주세요.", "어제", AlarmActionType.NONE,
            ),
            AlarmModel(
                4, AlarmType.READ_ALARM, "🚨 엄마의 케어콜이 연속 2회 부재중 상태입니다. 확인해 주세요!", "어제", AlarmActionType.CALL_COMPLETED,
            ),
            AlarmModel(
                5, AlarmType.READ_ALARM, "✅ 김옥자님의 점심 케어콜을 완료했습니다.", "5월 18일", AlarmActionType.NONE,
            ),
        )
    }

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
                    painterResource(Res.drawable.ic_arrow_big_back),
                    contentDescription = "setting back",
                    modifier = modifier
                        .size(24.dp)
                        .clickable { onBack() },
                    tint = MediCareCallTheme.colors.black,
                )
            },
        )

        // 리스트 렌더링 (스크롤 지원 및 성능 최적화)
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(
                items = alarmList,
                key = { it.id }, // 고유 키 지정
            ) { alarm ->
                AlarmItem(
                    model = alarm,
                    onCallClick = {
                        // TODO: 대상자에게 전화걸기 로직 연결
                        val phoneNumber = "010-1234-5678" // 실제로는 대상자의 전화번호가 들어가야 함
                        uriHandler.openUri("tel:$phoneNumber")
                    },
                )
            }
        }
    }
}

@Preview
@Composable
private fun AlarmScreenPreview() {
    MediCareCallTheme {
        AlarmScreen(
            onBack = {},
        )
    }
}
