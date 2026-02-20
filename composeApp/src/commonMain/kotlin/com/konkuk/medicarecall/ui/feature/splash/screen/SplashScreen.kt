package com.konkuk.medicarecall.ui.feature.splash.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.*
import com.konkuk.medicarecall.resources.bg_splash_new
import com.konkuk.medicarecall.ui.feature.splash.viewmodel.SplashViewModel
import com.konkuk.medicarecall.ui.model.NavigationDestination
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme

@Composable
fun SplashScreen(
    navigateToLogin: () -> Unit = {},
    navigateToStart: () -> Unit = {},
    navigateToRegisterElder: () -> Unit = {},
    navigateToCareCallSetting: () -> Unit = {},
    navigateToPurchase: () -> Unit = {},
    navigateToHome: () -> Unit = {},
) {
    val viewModel: SplashViewModel = koinViewModel()

    val navigationDestination by viewModel.navigationDestination.collectAsStateWithLifecycle()

    LaunchedEffect(navigationDestination) {
        navigationDestination?.let { destination ->
            navigateToLogin()
            when (destination) {
                is NavigationDestination.GoToLogin -> navigateToStart()
                is NavigationDestination.GoToRegisterElder -> navigateToRegisterElder()
                is NavigationDestination.GoToTimeSetting -> navigateToCareCallSetting()
                is NavigationDestination.GoToPayment -> navigateToPurchase()
                is NavigationDestination.GoToHome -> navigateToHome()
            }
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(MediCareCallTheme.colors.main),
    ) {
        Image(
            painterResource(Res.drawable.bg_splash_new),
            "Medicare Call 스플래시",
            Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Composable
private fun SplashScreenPreview() {
    MediCareCallTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(MediCareCallTheme.colors.main),
        ) {
            Image(
                painterResource(Res.drawable.bg_splash_new),
                "Medicare Call 스플래시",
                Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}
