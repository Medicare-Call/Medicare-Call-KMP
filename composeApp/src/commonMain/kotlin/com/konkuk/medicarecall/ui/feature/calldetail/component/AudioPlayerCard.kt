package com.konkuk.medicarecall.ui.feature.calldetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.ic_button_play
import com.konkuk.medicarecall.resources.ic_button_stop
import com.konkuk.medicarecall.ui.theme.MediCareCallTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun AudioPlayerCard(
    modifier: Modifier = Modifier,
    title: String = "오전 8:31 케어콜",
    currentTime: String = "01:58",
    remainingTime: String = "-00:33",
    progress: Float = 0.8f, // 0.0 ~ 1.0 진행률
    isPlaying: Boolean = false,
    onTogglePlay: () -> Unit = {},
    playIcon: Painter? = null,
) {
    val resolvedPlayIcon = playIcon ?: painterResource(Res.drawable.ic_button_play)
    val playerHeight = if (isPlaying) 12.dp else 6.dp
    val playStop = if (isPlaying) painterResource(Res.drawable.ic_button_stop) else resolvedPlayIcon
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MediCareCallTheme.colors.white)
            .padding(14.dp, 16.dp),
    ) {
        Text(
            text = "케어콜 다시 듣기",
            style = MediCareCallTheme.typography.R_14,
            color = MediCareCallTheme.colors.gray4,
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 케어콜 시간
        Text(
            text = title,
            style = MediCareCallTheme.typography.SB_16,
            color = MediCareCallTheme.colors.black,
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 슬라이더
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(playerHeight)
                .clip(RoundedCornerShape(100.dp))
                .background(Color(0xFFECECEC)),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress.coerceIn(0f, 1f))
                    .height(playerHeight)
                    .clip(RoundedCornerShape(100.dp))
                    .background(Color.Black),
            )
        }
        Spacer(modifier = Modifier.height(11.dp))

        // 재생 시간
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = currentTime, style = MediCareCallTheme.typography.R_14, color = MediCareCallTheme.colors.gray6)
            Text(text = remainingTime, style = MediCareCallTheme.typography.R_14, color = MediCareCallTheme.colors.gray6)
        }

        Spacer(modifier = Modifier.height(4.dp))

        // 재생 버튼
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = playStop,
                contentDescription = "Play/Stop",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onTogglePlay() },
                tint = Color.Black,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAudioPlayerCard() {
    MediCareCallTheme {

        AudioPlayerCard(
            title = "오전 8:31 케어콜",
            currentTime = "01:58",
            remainingTime = "-00:33",
            progress = 0.8f,
            isPlaying = false,
            onTogglePlay = {},
            playIcon = painterResource(Res.drawable.ic_button_play),
        )
    }
}
