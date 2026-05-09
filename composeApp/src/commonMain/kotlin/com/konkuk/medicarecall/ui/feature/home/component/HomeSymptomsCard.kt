package com.konkuk.medicarecall.ui.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.medicarecall.domain.model.type.HomeStatusType
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.ic_heart_small
import com.konkuk.medicarecall.resources.ic_warning
import com.konkuk.medicarecall.ui.theme.Black
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import com.konkuk.medicarecall.ui.theme.White
import com.konkuk.medicarecall.ui.theme.gray1
import com.konkuk.medicarecall.ui.theme.gray4
import org.jetbrains.compose.resources.vectorResource

@Composable
fun HomeSymptomsCard(
    symptomsTitle: String,
    symptomsDescription: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier.background(White, RoundedCornerShape(16.dp))
            .border(1.dp, gray1, RoundedCornerShape(16.dp)),
    ) {
        Column(
            modifier = Modifier.fillMaxHeight().padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(imageVector = vectorResource(Res.drawable.ic_warning), contentDescription = "특이사항", tint = gray4)
                Text("반복되는 증상", color = gray4, style = MediCareCallTheme.typography.R_14)
            }
            Spacer(Modifier.height(15.dp))
            Spacer(Modifier.height(8.dp))
            Column {
                Text(symptomsTitle, color = Black, style = MediCareCallTheme.typography.B_20)
                Spacer(Modifier.height(6.dp))
                Text(symptomsDescription, color = gray4, style = MediCareCallTheme.typography.M_16.copy(fontSize = 12.sp))
            }
        }
    }
}
