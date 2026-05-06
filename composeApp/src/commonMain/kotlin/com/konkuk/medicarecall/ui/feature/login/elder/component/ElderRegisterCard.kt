package com.konkuk.medicarecall.ui.feature.login.elder.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.ic_right_arrow_small
import com.konkuk.medicarecall.resources.img_register_card
import com.konkuk.medicarecall.resources.img_unregister_card
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ElderRegisterCard(
    title: String,
    description: String,
    imageRes: DrawableResource,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 18.dp)
            .background(MediCareCallTheme.colors.white, RoundedCornerShape(20.dp))
            .border(1.dp, MediCareCallTheme.colors.gray1, RoundedCornerShape(20.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(7.dp),
    ) {
        Text(
            text = title,
            style = MediCareCallTheme.typography.B_17,
            color = MediCareCallTheme.colors.black,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(92.dp),
        ) {
            Row(
                modifier = Modifier.align(Alignment.TopStart),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Text(
                    text = description,
                    style = MediCareCallTheme.typography.M_12,
                    color = MediCareCallTheme.colors.gray5,
                )
                Icon(
                    painter = painterResource(Res.drawable.ic_right_arrow_small),
                    tint = Color.Unspecified,
                    contentDescription = "",
                    modifier = Modifier.size(12.dp),
                )
            }

            Icon(
                painter = painterResource(imageRes),
                tint = Color.Unspecified,
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 6.dp)
                    .size(width = 136.dp, height = 85.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ElderRegisterCardPreview() {
    MediCareCallTheme{
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            ElderRegisterCard(
                title = "처음 등록하는\n어르신이에요",
                description = "신규 어르신 등록",
                imageRes = Res.drawable.img_unregister_card,
                onClick = {},
            )
            ElderRegisterCard(
                title = "이미 등록된\n어르신이에요",
                description = "기존 어르신 등록",
                imageRes = Res.drawable.img_register_card,
                onClick = {},
            )
        }
    }
}
