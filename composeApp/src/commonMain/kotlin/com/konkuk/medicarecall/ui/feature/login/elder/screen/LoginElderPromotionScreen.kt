package com.konkuk.medicarecall.ui.feature.login.elder.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.ic_complete
import com.konkuk.medicarecall.ui.common.component.CTAButton
import com.konkuk.medicarecall.ui.common.component.DefaultTextField
import com.konkuk.medicarecall.ui.feature.login.elder.viewmodel.LoginElderPromotionUiState
import com.konkuk.medicarecall.ui.feature.login.elder.viewmodel.LoginElderPromotionViewModel
import com.konkuk.medicarecall.ui.feature.login.myinfo.component.LoginBackButton
import com.konkuk.medicarecall.ui.theme.Black
import com.konkuk.medicarecall.ui.theme.G500
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import com.konkuk.medicarecall.ui.theme.White
import com.konkuk.medicarecall.ui.type.CTAButtonType
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginElderPromotionScreen(
    onBack: () -> Unit,
    navigateToElderRegister: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginElderPromotionViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoginElderPromotionScreenLayout(
        onBack = onBack,
        navigateToElderRegister = navigateToElderRegister,
        uiState = uiState,
        onInputChanged = { viewModel.onPromotionCodeChange(it) },
        onConfirm = { viewModel.onConfirm() },
        onDismissRequest = { viewModel.onDismissRequest() },
        modifier = modifier
    )
}

@Composable
private fun LoginElderPromotionScreenLayout(
    onBack: () -> Unit,
    navigateToElderRegister: () -> Unit,
    uiState: LoginElderPromotionUiState,
    onInputChanged: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        LoginBackButton(onBack)
        Column(
            Modifier
                .weight(1f)
                .verticalScroll(scrollState),
        ) {
            Spacer(Modifier.height(30.dp))
            Text(
                "프로모션 코드\n입력하기",
                style = MediCareCallTheme.typography.B_26,
                color = MediCareCallTheme.colors.black,
            )
            Spacer(Modifier.height(30.dp))
            DefaultTextField(
                value = uiState.inputValue,
                onValueChange = onInputChanged,
                placeHolder = "코드를 입력해주세요",
            )
            Spacer(Modifier.height(30.dp))
            CTAButton(
                if (uiState.inputValue.matches("^[A-Z0-9]{8}\$".toRegex())) CTAButtonType.GREEN
                else CTAButtonType.DISABLED,
                text = "확인",
                onClick = { onConfirm() },
            )

            if (uiState.showDialog) {
                Dialog(
                    onDismissRequest = onDismissRequest,
                    properties = DialogProperties(
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true,
                    ),
                ) {
                    Surface(
                        Modifier.fillMaxWidth()
                            .wrapContentHeight(),
                        shape = RoundedCornerShape(14.dp),
                        color = White,
                    ) {
                        Column(
                            Modifier.padding(20.dp).padding(top = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Icon(
                                imageVector = vectorResource(Res.drawable.ic_complete),
                                contentDescription = "완료 아이콘",
                                modifier = Modifier.size(55.dp),
                            )
                            Spacer(Modifier.height(15.5.dp))
                            Text(
                                "인증되었습니다", style = MediCareCallTheme.typography.SB_18,
                                color = Black,
                            )
                            Spacer(Modifier.height(20.dp))
                            Box(
                                Modifier.fillMaxWidth()
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(G500)
                                    .clickable { navigateToElderRegister() },
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    "확인", color = White, style = MediCareCallTheme.typography.B_17,
                                    modifier = Modifier.padding(vertical = 14.dp),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
