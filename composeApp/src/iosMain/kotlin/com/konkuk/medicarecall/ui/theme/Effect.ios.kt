//package com.konkuk.medicarecall.ui.theme
//
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.drawWithCache
//import androidx.compose.ui.geometry.CornerRadius
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.geometry.Size
//import androidx.compose.ui.unit.Dp
//
//actual fun Modifier.figmaShadow(
//    group: ShadowGroup,
//    cornerRadius: Dp,
//): Modifier = this.drawWithCache {
//    val radiusPx = cornerRadius.toPx()
//
//    onDrawWithContent {
//        group.layers.forEach { layer ->
//            drawRoundRect(
//                color = layer.color,
//                topLeft = Offset(layer.offsetX.toPx(), layer.offsetY.toPx()),
//                size = Size(size.width, size.height),
//                cornerRadius = CornerRadius(radiusPx, radiusPx),
//            )
//        }
//        drawContent()
//    }
//}
