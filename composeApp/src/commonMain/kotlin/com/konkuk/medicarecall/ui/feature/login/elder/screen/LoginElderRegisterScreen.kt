package com.konkuk.medicarecall.ui.feature.login.elder.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.img_register_card
import com.konkuk.medicarecall.resources.img_unregister_card
import com.konkuk.medicarecall.ui.feature.login.elder.component.ElderRegisterCard
import com.konkuk.medicarecall.ui.feature.login.myinfo.component.LoginBackButton
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme

@Composable
fun LoginElderRegisterScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    navigateToNewElderRegister: () -> Unit,
    navigateToExistingElderRegister: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MediCareCallTheme.colors.bg)
            .padding(horizontal = 18.dp)
            .systemBarsPadding()
            .imePadding()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(60.dp),
    ) {
        LoginBackButton(onClick = onBack)
        Text(
            text = "어르신 등록하기",
            style = MediCareCallTheme.typography.B_26,
            color = MediCareCallTheme.colors.black,
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            ElderRegisterCard(
                title = "처음 등록하는\n어르신이에요",
                description = "신규 어르신 등록",
                imageRes = Res.drawable.img_unregister_card,
                onClick = navigateToNewElderRegister,
            )
            ElderRegisterCard(
                title = "이미 등록된\n어르신이에요",
                description = "기존 어르신 등록",
                imageRes = Res.drawable.img_register_card,
                onClick = navigateToExistingElderRegister,
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun LoginElderRegisterScreenPreview() {
    MediCareCallTheme{
        LoginElderRegisterScreen(
            onBack = {},
            navigateToNewElderRegister = {},
            navigateToExistingElderRegister = {},
        )
    }
}
