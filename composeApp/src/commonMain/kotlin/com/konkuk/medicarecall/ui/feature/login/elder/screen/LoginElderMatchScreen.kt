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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.img_elder
import com.konkuk.medicarecall.ui.common.component.CTAButton
import com.konkuk.medicarecall.ui.feature.login.myinfo.component.LoginBackButton
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import com.konkuk.medicarecall.ui.type.CTAButtonType
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoginElderMatchScreen(
    onBack: () -> Unit,
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
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        LoginBackButton(onClick = onBack)

        Text(
            text = "김옥자 어르신이\n맞으신가요?",
            style = MediCareCallTheme.typography.B_26,
            color = MediCareCallTheme.colors.black,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "입력하신 코드로 찾은 어르신이에요",
            style = MediCareCallTheme.typography.M_17,
            color = MediCareCallTheme.colors.gray5,
            textAlign = TextAlign.Center,
        )
        Image(
            painter = painterResource(Res.drawable.img_elder),
            contentDescription = "",
            modifier = Modifier.size(150.dp),
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            CTAButton(
                type = CTAButtonType.GREEN,
                text = "네, 맞아요",
                onClick = navigateToRegister,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MediCareCallTheme.colors.gray1, shape = RoundedCornerShape(14.dp))
                    .clickable(
                        interactionSource,
                        onClick = navigateToHome,
                        indication = null,
                    ),
            ) {
                Text(
                    text = "아니에요",
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

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun LoginElderMatchScreenPreview() {
    MediCareCallTheme(dynamicColor = false) {
        LoginElderMatchScreen(
            onBack = {},
            navigateToRegister = {},
            navigateToHome = {},
        )
    }
}
