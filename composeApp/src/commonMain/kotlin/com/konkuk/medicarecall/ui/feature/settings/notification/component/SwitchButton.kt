package com.konkuk.medicarecall.ui.feature.settings.notification.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.*
import com.konkuk.medicarecall.resources.img_switch_off
import com.konkuk.medicarecall.resources.img_switch_on
import org.jetbrains.compose.resources.painterResource

@Composable
fun SwitchButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val imageRes = if (checked) Res.drawable.img_switch_on else Res.drawable.img_switch_off

    Image(
        painter = painterResource(imageRes),
        contentDescription = null,
        modifier = modifier
            .size(width = 52.dp, height = 32.dp)
            .clickable { onCheckedChange(!checked) },
    )
}

@Composable
fun SwitchItemOffPreview() {
    SwitchButton(
        checked = false,
        onCheckedChange = {},
    )
}

@Composable
fun SwitchItemOnPreview() {
    SwitchButton(
        checked = true,
        onCheckedChange = {},
    )
}

@Composable
fun SwitchItemInteractivePreview() {
    var checked by remember { mutableStateOf(false) }
    SwitchButton(
        checked = checked,
        onCheckedChange = { checked = it },
        modifier = Modifier.clip(CircleShape),
    )
}
