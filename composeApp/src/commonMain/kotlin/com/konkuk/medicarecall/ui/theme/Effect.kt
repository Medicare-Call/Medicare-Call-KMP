package com.konkuk.medicarecall.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class ShadowLayer(
    val offsetX: Dp,
    val offsetY: Dp,
    val blurRadius: Dp,
    val color: Color,
)

@Immutable
data class ShadowGroup(
    val layers: List<ShadowLayer>,
)

@Immutable
data class MediCareCallShadows(
    val shadow01: ShadowGroup,
    val shadow02: ShadowGroup,
    val shadow03: ShadowGroup,
)

val defaultMediCareCallShadow = MediCareCallShadows(
    shadow01 = ShadowGroup(
        listOf(
            ShadowLayer(0.dp, 4.dp, 8.dp, Color(0xFF222222).copy(alpha = 0.02f)),
            ShadowLayer(4.dp, 0.dp, 8.dp, Color(0xFF222222).copy(alpha = 0.02f)),
            ShadowLayer(-4.dp, 0.dp, 8.dp, Color(0xFF222222).copy(alpha = 0.02f)),
        ),
    ),
    shadow02 = ShadowGroup(
        listOf(
            ShadowLayer(0.dp, 4.dp, 12.dp, Color(0xFF222222).copy(alpha = 0.04f)),
            ShadowLayer(4.dp, 0.dp, 12.dp, Color(0xFF222222).copy(alpha = 0.04f)),
            ShadowLayer(-4.dp, 0.dp, 12.dp, Color(0xFF222222).copy(alpha = 0.04f)),
        ),
    ),
    shadow03 = ShadowGroup(
        listOf(
            ShadowLayer(0.dp, 4.dp, 16.dp, Color(0xFF222222).copy(alpha = 0.04f)),
            ShadowLayer(4.dp, 0.dp, 16.dp, Color(0xFF222222).copy(alpha = 0.04f)),
            ShadowLayer(-4.dp, 0.dp, 16.dp, Color(0xFF222222).copy(alpha = 0.04f)),
        ),
    ),
)

val LocalMediCareCallShadowProvider = staticCompositionLocalOf { defaultMediCareCallShadow }

expect fun Modifier.figmaShadow(
    group: ShadowGroup,
    cornerRadius: Dp = 14.dp,
): Modifier
