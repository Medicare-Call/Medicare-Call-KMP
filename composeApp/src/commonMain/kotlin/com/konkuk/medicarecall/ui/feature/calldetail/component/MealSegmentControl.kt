package com.konkuk.medicarecall.ui.feature.calldetail.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import com.konkuk.medicarecall.ui.theme.figmaShadow

@Composable
fun MealSegmentedControl(
    items: List<String> = listOf("아침", "점심", "저녁"),
    selectedItem: String,
    onItemClick: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(35.dp)
            .background(
                color = MediCareCallTheme.colors.gray1,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(2.dp),
    ) {
        val selectedIndex = items.indexOf(selectedItem)

        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val itemWidth = maxWidth / items.size
            val xOffset by animateDpAsState(targetValue = itemWidth * selectedIndex)

            Box(
                modifier = Modifier
                    .offset(x = xOffset)
                    .width(itemWidth)
                    .fillMaxHeight()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .figmaShadow(
                            group = MediCareCallTheme.shadow.shadow01,
                            cornerRadius = 8.dp,
                        )
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp),
                        ),
                )
            }
        }

        Row(modifier = Modifier.fillMaxSize()) {
            items.forEach { item ->
                val isSelected = item == selectedItem

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                        ) { onItemClick(item) },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = item,
                        style = MediCareCallTheme.typography.R_15,
                        color = if (isSelected) {
                            MediCareCallTheme.colors.gray8
                        } else {
                            MediCareCallTheme.colors.gray3
                        },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMealSegmentedControl() {
    var selectedMeal by remember { mutableStateOf("아침") }

    MediCareCallTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MediCareCallTheme.colors.bg),
            contentAlignment = Alignment.Center,
        ) {
            MealSegmentedControl(
                items = listOf("아침", "점심", "저녁"),
                selectedItem = selectedMeal,
                onItemClick = { selectedMeal = it },
            )
        }
    }
}
