package com.konkuk.medicarecall.ui.feature.settings.notice.screen

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
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.*
import com.konkuk.medicarecall.ui.feature.settings.notice.component.AnnouncementCard
import com.konkuk.medicarecall.ui.feature.settings.component.SettingsTopAppBar
import com.konkuk.medicarecall.ui.feature.settings.notice.viewmodel.SettingsNoticeViewModel
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsNoticeScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    navigateToNoticeDetail: (noticeId: Long) -> Unit = {},
    viewModel: SettingsNoticeViewModel = koinViewModel(),
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
            modifier = modifier,
            title = "공지사항",
            leftIcon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_settings_back),
                    contentDescription = "go_back",
                    modifier = modifier
                        .size(24.dp)
                        .clickable { onBack() },
                    tint = Color.Black,
                )
            },
        )
        Column(
            modifier = modifier.verticalScroll(scrollState),
        ) {
            if (uiState.errorMessage != null) {
                AnnouncementCard("공지사항 오류 발생", uiState.errorMessage ?: "", onClick = {})
            } else {
                uiState.noticeList.forEach { notice ->
                    AnnouncementCard(
                        title = notice.title,
                        date = notice.publishedAt.replace("-", "."),
                        onClick = {
                            navigateToNoticeDetail(notice.id)
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsNoticeScreenPreview() {
    MediCareCallTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MediCareCallTheme.colors.bg)
                .statusBarsPadding(),
        ) {
            SettingsTopAppBar(
                title = "공지사항",
                leftIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_settings_back),
                        contentDescription = "go_back",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { },
                        tint = Color.Black,
                    )
                },
            )
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                AnnouncementCard("서비스 업데이트 안내", "2024.01.15", onClick = {})
                AnnouncementCard("새해 인사", "2024.01.01", onClick = {})
            }
        }
    }
}
