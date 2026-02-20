@file:Suppress("ConstructorParameterNaming")

package com.konkuk.medicarecall.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.pretendard_bold
import com.konkuk.medicarecall.resources.pretendard_medium
import com.konkuk.medicarecall.resources.pretendard_regular
import com.konkuk.medicarecall.resources.pretendard_semibold
import org.jetbrains.compose.resources.Font

@Composable
fun mediCareCallFontBold() = FontFamily(Font(Res.font.pretendard_bold))

@Composable
fun mediCareCallFontSemiBold() = FontFamily(Font(Res.font.pretendard_semibold))

@Composable
fun mediCareCallFontMedium() = FontFamily(Font(Res.font.pretendard_medium))

@Composable
fun mediCareCallFontRegular() = FontFamily(Font(Res.font.pretendard_regular))

@Suppress("ConstructorParameterNaming")
@Immutable
data class MediCareCallTypography(
    // Title
    val B_30: TextStyle,
    val B_28: TextStyle,
    val B_26: TextStyle,
    val SB_24: TextStyle,
    val SB_22: TextStyle,
    val B_20: TextStyle,
    val SB_20: TextStyle,
    val M_20: TextStyle,

    // Body
    val SB_18: TextStyle,
    val R_18: TextStyle,
    val B_17: TextStyle,
    val M_17: TextStyle,
    val R_17: TextStyle,
    val SB_16: TextStyle,
    val M_16: TextStyle,
    val R_16: TextStyle,

    // Caption
    val R_15: TextStyle,
    val SB_14: TextStyle,
    val R_14: TextStyle,
)

@Composable
fun createMediCareCallTypography(): MediCareCallTypography {
    val bold = mediCareCallFontBold()
    val semiBold = mediCareCallFontSemiBold()
    val medium = mediCareCallFontMedium()
    val regular = mediCareCallFontRegular()

    return MediCareCallTypography(
        // Title
        B_30 = TextStyle(
            fontFamily = bold,
            fontSize = 30.sp,
            lineHeight = 1.3.em,
            letterSpacing = (-0.02).em,
        ),
        B_28 = TextStyle(
            fontFamily = bold,
            fontSize = 28.sp,
            lineHeight = 1.3.em,
            letterSpacing = (-0.02).em,
        ),
        B_26 = TextStyle(
            fontFamily = bold,
            fontSize = 26.sp,
            lineHeight = 1.3.em,
            letterSpacing = (-0.02).em,
        ),
        SB_24 = TextStyle(
            fontFamily = semiBold,
            fontSize = 24.sp,
            lineHeight = 1.3.em,
            letterSpacing = (-0.02).em,
        ),
        SB_22 = TextStyle(
            fontFamily = semiBold,
            fontSize = 22.sp,
            lineHeight = 1.3.em,
            letterSpacing = (-0.02).em,
        ),
        B_20 = TextStyle(
            fontFamily = bold,
            fontSize = 20.sp,
            lineHeight = 1.3.em,
            letterSpacing = (-0.02).em,
        ),
        SB_20 = TextStyle(
            fontFamily = semiBold,
            fontSize = 20.sp,
            lineHeight = 1.3.em,
            letterSpacing = (-0.02).em,
        ),
        M_20 = TextStyle(
            fontFamily = medium,
            fontSize = 20.sp,
            lineHeight = 1.3.em,
            letterSpacing = (-0.02).em,
        ),

        // Body
        SB_18 = TextStyle(
            fontFamily = semiBold,
            fontSize = 18.sp,
            lineHeight = 1.6.em,
            letterSpacing = 0.02.em,
        ),
        R_18 = TextStyle(
            fontFamily = regular,
            fontSize = 18.sp,
            lineHeight = 1.6.em,
        ),
        B_17 = TextStyle(
            fontFamily = bold,
            fontSize = 17.sp,
            lineHeight = 1.6.em,
            letterSpacing = 0.02.em,
        ),
        M_17 = TextStyle(
            fontFamily = medium,
            fontSize = 17.sp,
            lineHeight = 1.6.em,
            letterSpacing = 0.02.em,
        ),
        R_17 = TextStyle(
            fontFamily = regular,
            fontSize = 17.sp,
            lineHeight = 1.6.em,
        ),
        SB_16 = TextStyle(
            fontFamily = semiBold,
            fontSize = 16.sp,
            lineHeight = 1.6.em,
        ),
        M_16 = TextStyle(
            fontFamily = medium,
            fontSize = 16.sp,
            lineHeight = 1.6.em,
        ),
        R_16 = TextStyle(
            fontFamily = regular,
            fontSize = 16.sp,
            lineHeight = 1.6.em,
        ),

        // Caption
        R_15 = TextStyle(
            fontFamily = regular,
            fontSize = 15.sp,
            lineHeight = 1.5.em,
            letterSpacing = (0.01).em,
        ),
        SB_14 = TextStyle(
            fontFamily = semiBold,
            fontSize = 14.sp,
            lineHeight = 1.5.em,
            letterSpacing = (0.01).em,
        ),
        R_14 = TextStyle(
            fontFamily = regular,
            fontSize = 14.sp,
            lineHeight = 1.5.em,
            letterSpacing = (0.01).em,
        ),
    )
}

val defaultMediCareCallTypography = MediCareCallTypography(
    B_30 = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold, lineHeight = 1.3.em, letterSpacing = (-0.02).em),
    B_28 = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold, lineHeight = 1.3.em, letterSpacing = (-0.02).em),
    B_26 = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Bold, lineHeight = 1.3.em, letterSpacing = (-0.02).em),
    SB_24 = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold, lineHeight = 1.3.em, letterSpacing = (-0.02).em),
    SB_22 = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.SemiBold, lineHeight = 1.3.em, letterSpacing = (-0.02).em),
    B_20 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, lineHeight = 1.3.em, letterSpacing = (-0.02).em),
    SB_20 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold, lineHeight = 1.3.em, letterSpacing = (-0.02).em),
    M_20 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, lineHeight = 1.3.em, letterSpacing = (-0.02).em),
    SB_18 = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold, lineHeight = 1.6.em, letterSpacing = 0.02.em),
    R_18 = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal, lineHeight = 1.6.em),
    B_17 = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold, lineHeight = 1.6.em, letterSpacing = 0.02.em),
    M_17 = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Medium, lineHeight = 1.6.em, letterSpacing = 0.02.em),
    R_17 = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Normal, lineHeight = 1.6.em),
    SB_16 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold, lineHeight = 1.6.em),
    M_16 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, lineHeight = 1.6.em),
    R_16 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, lineHeight = 1.6.em),
    R_15 = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Normal, lineHeight = 1.5.em, letterSpacing = (0.01).em),
    SB_14 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, lineHeight = 1.5.em, letterSpacing = (0.01).em),
    R_14 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, lineHeight = 1.5.em, letterSpacing = (0.01).em),
)

val LocalMedicareCallTypographyProvider = staticCompositionLocalOf { defaultMediCareCallTypography }

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
)
