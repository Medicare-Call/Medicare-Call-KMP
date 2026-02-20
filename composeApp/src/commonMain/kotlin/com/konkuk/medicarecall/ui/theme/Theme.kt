package com.konkuk.medicarecall.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.konkuk.medicarecall.platform.platformDynamicColorScheme

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
)

@Composable
fun MediCareCallTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor -> platformDynamicColorScheme(darkTheme) ?: if (darkTheme) DarkColorScheme else LightColorScheme
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}

object MediCareCallTheme {
    val colors: MediCareCallColors
        @Composable
        @ReadOnlyComposable
        get() = LocalMediCareCallColorsProvider.current

    val typography: MediCareCallTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalMedicareCallTypographyProvider.current

    val shadow: MediCareCallShadows
        @Composable
        @ReadOnlyComposable
        get() = LocalMediCareCallShadowProvider.current
}
