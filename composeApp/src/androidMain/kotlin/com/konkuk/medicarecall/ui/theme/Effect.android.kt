package com.konkuk.medicarecall.ui.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp

actual fun Modifier.figmaShadow(
    group: ShadowGroup,
    cornerRadius: Dp,
): Modifier = this.drawWithCache {
    val radiusPx = cornerRadius.toPx()
    val paint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        color = 0
    }

    onDrawWithContent {
        group.layers.forEach { layer ->
            paint.setShadowLayer(
                layer.blurRadius.toPx(),
                layer.offsetX.toPx(),
                layer.offsetY.toPx(),
                layer.color.toArgb(),
            )
            drawIntoCanvas { canvas ->
                val nc = canvas.nativeCanvas
                nc.drawRoundRect(
                    0f,
                    0f,
                    size.width,
                    size.height,
                    radiusPx,
                    radiusPx,
                    paint,
                )
            }
        }

        paint.clearShadowLayer()

        drawIntoCanvas { canvas ->
            val nc = canvas.nativeCanvas
            nc.drawRoundRect(
                0f,
                0f,
                size.width,
                size.height,
                radiusPx,
                radiusPx,
                paint,
            )
        }

        drawContent()
    }
}
