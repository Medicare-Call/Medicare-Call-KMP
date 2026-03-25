package com.konkuk.medicarecall.ui.feature.home.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.medicarecall.domain.model.type.HomeStatusType
import com.konkuk.medicarecall.domain.model.type.MedicationTime
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.ic_arrow_small_right
import com.konkuk.medicarecall.ui.theme.BG
import com.konkuk.medicarecall.ui.theme.Black
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import com.konkuk.medicarecall.ui.theme.Positive
import com.konkuk.medicarecall.ui.theme.White
import com.konkuk.medicarecall.ui.theme.figmaShadow
import com.konkuk.medicarecall.ui.theme.gray1
import com.konkuk.medicarecall.ui.theme.gray3
import com.konkuk.medicarecall.ui.theme.gray4
import com.konkuk.medicarecall.ui.theme.gray8
import org.jetbrains.compose.resources.vectorResource

@Composable
fun HomeStatusDetailItem(
    selectedTime: MedicationTime,
    onChangeTime: (MedicationTime) -> Unit,
    mealStatus: HomeStatusType,
    medicineStatus: HomeStatusType,
    sleepStatus: HomeStatusType,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .background(White, RoundedCornerShape(16.dp))
            .border(1.dp, gray1, RoundedCornerShape(16.dp))
            .fillMaxWidth(),
    ) {
        Column(Modifier.padding(12.dp)) {
            BoxWithConstraints(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = gray1,
                        shape = RoundedCornerShape(8.dp),
                    )
                    .padding(2.dp), // 내부 여백 (인디케이터가 꽉 차지 않게)
            ) {
                val segmentWidth = maxWidth / 3

                // 하얀색 인디케이터가 이동할 X 좌표를 애니메이션으로 처리
                val indicatorOffset by animateDpAsState(
                    targetValue = segmentWidth * selectedTime.ordinal,
                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
                    label = "indicatorOffset",
                )

                // 1. 움직이는 하얀색 배경 (인디케이터)
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                ) {
                    Box(
                        modifier = Modifier
                            .width(segmentWidth)
                            .fillMaxHeight()
                            .offset(x = indicatorOffset) // 계산된 위치로 이동
                            .figmaShadow(MediCareCallTheme.shadow.shadow01) // 살짝 그림자 추가 (옵션)
                            .background(
                                color = White,
                                shape = RoundedCornerShape(8.dp),
                            ),
                    )

                    // 2. 글자(Text) 영역
                    Row(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        MedicationTime.entries.forEach {
                            Box(
                                modifier = Modifier
                                    .weight(1f) // Row 안에서 동일한 비율로 영역 차지
                                    .fillMaxHeight()
                                    .padding(vertical = 6.dp)
                                    .clickable(
                                        // 클릭 시 기본으로 생기는 물결 효과(Ripple) 제거 (더 깔끔함)
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                    ) {
                                        onChangeTime(it)
                                    },
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = it.displayName,
                                    fontSize = 16.sp,
                                    // 선택 여부에 따라 텍스트 색상과 굵기 변경
                                    color = if (selectedTime == it) gray8 else gray3,
                                    style = if (selectedTime == it) MediCareCallTheme.typography.SB_15
                                    else MediCareCallTheme.typography.R_15,
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(14.dp))
            Box(
                Modifier.fillMaxWidth()
                    .background(BG, RoundedCornerShape(8.dp)),
            ) {
                Row(Modifier.padding(horizontal = 12.dp, vertical = 16.dp)) {
                    Text("케어콜 상태", color = gray4, style = MediCareCallTheme.typography.R_14)
                    Spacer(Modifier.width(10.dp))
                    Text("완료", color = Positive, style = MediCareCallTheme.typography.SB_14) // TODO
                }
            }
            Spacer(Modifier.height(20.dp))
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 2.dp),
            ) {
                Row(
                    Modifier.weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("식사", color = Black, style = MediCareCallTheme.typography.R_15)
                    HomeStatusChip(statusType = mealStatus)
                }
                Spacer(Modifier.width(32.dp))
                Row(
                    Modifier.weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("복약", color = Black, style = MediCareCallTheme.typography.R_15)
                    HomeStatusChip(statusType = medicineStatus)
                }
            }
            Spacer(Modifier.height(20.dp))
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 2.dp),
            ) {
                Row(
                    Modifier.weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("수면", color = Black, style = MediCareCallTheme.typography.R_15)
                    HomeStatusChip(statusType = sleepStatus)
                }
                Spacer(Modifier.width(32.dp))
                Spacer(Modifier.weight(1f))

            }

            Spacer(Modifier.height(16.dp))
            Row(
                Modifier.padding(horizontal = 4.dp, vertical = 4.dp).align(Alignment.End),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("자세히 보기", color = gray8, style = MediCareCallTheme.typography.R_14.copy(fontSize = 12.sp))
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_arrow_small_right),
                    contentDescription = "자세히 보기", tint = gray8,
                )
            }

        }
    }
}
