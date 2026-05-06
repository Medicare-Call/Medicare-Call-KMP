package com.konkuk.medicarecall.ui.feature.login.elder.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.img_settings_center
import com.konkuk.medicarecall.ui.common.component.CTAButton
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import com.konkuk.medicarecall.ui.type.CTAButtonType
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoginFinishRegisterPreviousScreen(
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MediCareCallTheme.colors.bg)
            .systemBarsPadding()
            .imePadding()
            .padding(top = 172.dp)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        Image(
            painter = painterResource(Res.drawable.img_settings_center),
            contentDescription = "어르신 등록 완료 체크",
            modifier = Modifier.size(66.dp),
        )
        Text(
            text = "추가로 등록하실\n어르신이 있으신가요?",
            style = MediCareCallTheme.typography.B_26,
            color = MediCareCallTheme.colors.black,
            textAlign = TextAlign.Center,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            CTAButton(
                type = CTAButtonType.GREEN,
                text = "네, 있어요",
                onClick = navigateToRegister, // 어르신 등록하는 화면으로
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MediCareCallTheme.colors.gray1, shape = RoundedCornerShape(14.dp))
                    .clickable(
                        interactionSource,
                        onClick = navigateToHome, // 하루 요약 화면으로
                        indication = null,
                    ),
            ) {
                Text(
                    text = "아니요, 없어요",
                    color = MediCareCallTheme.colors.gray6,
                    style = MediCareCallTheme.typography.B_17,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .align(Alignment.Center),
                )
            }
        }
    }
}
