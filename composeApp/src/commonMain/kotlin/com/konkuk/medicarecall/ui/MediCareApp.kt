package com.konkuk.medicarecall.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.konkuk.medicarecall.ui.navigation.NavGraph
import com.konkuk.medicarecall.ui.navigation.component.MainBottomBar
import com.konkuk.medicarecall.ui.navigation.component.MainTab
import com.konkuk.medicarecall.ui.navigation.rememberMainNavigator
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme

@Composable
fun MediCareApp() {
    MediCareCallTheme {
        val navigator = rememberMainNavigator()
        Scaffold(
            modifier = Modifier.background(MediCareCallTheme.colors.bg),
            containerColor = MediCareCallTheme.colors.bg,
            contentWindowInsets = WindowInsets.systemBars.only(WindowInsetsSides.Horizontal),
            bottomBar = {
                MainBottomBar(
                    visible = navigator.shouldShowBottomBar(),
                    tabs = MainTab.entries.toList(),
                    currentTab = navigator.currentTab,
                    onTabSelected = {
                        navigator.navigateToMainTab(it)
                    },
                )
            },
        ) { innerPadding ->
            NavGraph(
                navigator = navigator,
                modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
            )
        }
    }
}
