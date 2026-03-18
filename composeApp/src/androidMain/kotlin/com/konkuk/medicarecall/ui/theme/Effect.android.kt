package com.konkuk.medicarecall.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.Dp

actual fun Modifier.figmaShadow(
    group: ShadowGroup,
    cornerRadius: Dp,
): Modifier = group.layers.fold(this) { acc, layer ->
    acc.shadow(
        elevation = layer.blurRadius,
        shape = RoundedCornerShape(cornerRadius),
        ambientColor = layer.color,
        spotColor = layer.color,
        clip = false
    )
}
