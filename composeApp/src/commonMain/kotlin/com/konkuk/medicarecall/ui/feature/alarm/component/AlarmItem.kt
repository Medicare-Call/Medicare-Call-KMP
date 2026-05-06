package com.konkuk.medicarecall.ui.feature.alarm.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.konkuk.medicarecall.domain.model.type.AlarmType
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.pretendard_medium
import com.konkuk.medicarecall.ui.model.AlarmModel
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import com.konkuk.medicarecall.ui.type.AlarmActionType
import org.jetbrains.compose.resources.Font

@Composable
fun AlarmItem(
    model: AlarmModel,
    onCallClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (model.type == AlarmType.NEW_ALARM) MediCareCallTheme.colors.g50 else MediCareCallTheme.colors.bg

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // 내용과 시간을 한 문장처럼 자연스럽게 렌더링
        val annotatedText = buildAnnotatedString {
            withStyle(style = MediCareCallTheme.typography.R_15.toSpanStyle().copy(
                color = MediCareCallTheme.colors.black
            )) {
                append(model.message)
            }
            append(" ")
            withStyle(style = MediCareCallTheme.typography.R_15.toSpanStyle().copy(
                color = MediCareCallTheme.colors.gray4
            )) {
                append(model.time)
            }
        }

        Text(
            text = annotatedText,
            modifier = Modifier.weight(1f),
            lineHeight = 20.sp,
        )

        // 우측 액션 버튼 (ActionType이 NONE이 아닐 때만 렌더링)
        if (model.actionType != AlarmActionType.NONE) {
            Spacer(modifier = Modifier.width(20.dp))

            val isCallActive = model.actionType == AlarmActionType.CALL_ACTIVE

            val buttonBgColor = if (isCallActive) MediCareCallTheme.colors.main else MediCareCallTheme.colors.gray2
            val buttonTextColor = if (isCallActive) MediCareCallTheme.colors.bg else MediCareCallTheme.colors.gray6
            val buttonText = if (isCallActive) "전화하기" else "전화 완료"

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(buttonBgColor)
                    // 클릭 이벤트는 CALL_ACTIVE 상태일 때만 활성화
                    .clickable(enabled = isCallActive) {
                        onCallClick()
                    }
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = buttonText,
                    color = buttonTextColor,
                    fontFamily = FontFamily(Font(Res.font.pretendard_medium)),
                    fontSize = 12.sp,
                    lineHeight = 1.5.em
                )
            }
        }
    }
}
