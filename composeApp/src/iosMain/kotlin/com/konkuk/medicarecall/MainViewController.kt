package com.konkuk.medicarecall

import androidx.compose.ui.window.ComposeUIViewController
import com.konkuk.medicarecall.data.di.ApiModule
import com.konkuk.medicarecall.data.di.NetworkModule
import com.konkuk.medicarecall.di.iosModule
import com.konkuk.medicarecall.ui.navigation.NavGraph
import com.konkuk.medicarecall.ui.navigation.rememberMainNavigator
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule
import org.koin.ksp.generated.module

fun initKoin() {
    startKoin {
        modules(
            defaultModule,
            ApiModule().module,
            NetworkModule().module,
            iosModule,
        )
    }
}

fun MainViewController() = ComposeUIViewController {
    MediCareCallTheme {
        val navigator = rememberMainNavigator()
        NavGraph(navigator = navigator)
    }
}
