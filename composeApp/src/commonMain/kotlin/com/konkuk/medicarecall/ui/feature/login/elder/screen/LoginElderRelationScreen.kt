package com.konkuk.medicarecall.ui.feature.login.elder.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.domain.model.type.Relationship
import com.konkuk.medicarecall.ui.common.component.CTAButton
import com.konkuk.medicarecall.ui.common.component.DefaultDropdown
import com.konkuk.medicarecall.ui.feature.login.elder.viewmodel.LoginElderViewModel
import com.konkuk.medicarecall.ui.feature.login.myinfo.component.LoginBackButton
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import com.konkuk.medicarecall.ui.type.CTAButtonType
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginElderRelationScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    navigateToFinishRegister: () -> Unit = {},
    viewModel: LoginElderViewModel = koinViewModel(),
) {
    var isComplete by rememberSaveable { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    LoginElderRelationScreenContent(
        modifier = modifier,
        isComplete = isComplete,
        scrollState = scrollState,
        onBack = onBack,
        onOptionSelect = { newValue ->
            val rel = Relationship.entries.firstOrNull {
                it.displayName == newValue
            } ?: Relationship.ACQUAINTANCE
            viewModel.updateElderRelationship(rel)
            isComplete = true
        },
        onNextClick = navigateToFinishRegister,
    )
}

@Composable
private fun LoginElderRelationScreenContent(
    modifier: Modifier = Modifier,
    isComplete: Boolean,
    scrollState: androidx.compose.foundation.ScrollState,
    onBack: () -> Unit,
    onOptionSelect: (String) -> Unit,
    onNextClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MediCareCallTheme.colors.bg)
            .padding(horizontal = 20.dp)
            .systemBarsPadding()
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        LoginBackButton(onClick = onBack)
        Text(
            text = "어르신과의\n관계를 선택해주세요",
            style = MediCareCallTheme.typography.B_26,
            color = MediCareCallTheme.colors.black,
        )

        DefaultDropdown(
            enumList = Relationship.entries.map { it.displayName }.toList(),
            placeHolder = "관계 선택하기",
            category = "어르신과의 관계",
            scrollState,
            onOptionSelect = onOptionSelect,
        )
        CTAButton(
            type = if (isComplete) CTAButtonType.GREEN else CTAButtonType.DISABLED,
            text = "다음",
            onClick = onNextClick,
        )
    }
}

@Preview
@Composable
private fun LoginElderRelationScreenPreview() {
    MediCareCallTheme {
        LoginElderRelationScreenContent(
            isComplete = true,
            scrollState = rememberScrollState(),
            onBack = {},
            onOptionSelect = {},
            onNextClick = {},
        )
    }
}
