package com.konkuk.medicarecall.ui.common.component

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@Composable
fun WheelPicker(
    items: List<String>,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    itemHeight: Dp = 48.dp,
    visibleItemCount: Int = 3,
    textStyle: TextStyle = TextStyle.Default,
    selectedTextColor: Color = Color.Black,
    unselectedTextColor: Color = Color.Gray,
) {
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = selectedIndex,
    )
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val totalHeight = itemHeight * visibleItemCount
    val halfVisibleItems = visibleItemCount / 2

    // Detect selected item change from scroll
    val currentSelectedIndex by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val viewportCenter = layoutInfo.viewportStartOffset + layoutInfo.viewportSize.height / 2
            layoutInfo.visibleItemsInfo
                .minByOrNull { kotlin.math.abs((it.offset + it.size / 2) - viewportCenter) }
                ?.index
                ?.minus(halfVisibleItems)
                ?.coerceIn(0, items.size - 1) ?: selectedIndex
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { currentSelectedIndex }
            .distinctUntilChanged()
            .collect { index ->
                onSelectedIndexChange(index)
            }
    }

    LaunchedEffect(selectedIndex) {
        if (currentSelectedIndex != selectedIndex) {
            listState.animateScrollToItem(selectedIndex)
        }
    }

    Box(
        modifier = modifier
            .height(totalHeight)
            .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
            .drawWithContent {
                drawContent()
                // Top fade
                drawRect(
                    brush = Brush.verticalGradient(
                        0f to Color.Transparent,
                        0.3f to Color.Black,
                        0.7f to Color.Black,
                        1f to Color.Transparent,
                    ),
                    blendMode = BlendMode.DstIn,
                )
            },
    ) {
        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            modifier = Modifier.fillMaxWidth(),
        ) {
            // Padding items at the top
            items(halfVisibleItems) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight),
                )
            }

            items(items.size) { index ->
                val isSelected = index == currentSelectedIndex
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = items[index],
                        style = textStyle,
                        color = if (isSelected) selectedTextColor else unselectedTextColor,
                        textAlign = TextAlign.Center,
                    )
                }
            }

            // Padding items at the bottom
            items(halfVisibleItems) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight),
                )
            }
        }
    }
}
