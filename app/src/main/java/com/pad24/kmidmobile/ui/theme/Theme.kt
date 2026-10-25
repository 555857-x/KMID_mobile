package com.pad24.kmidmobile.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Warna diambil dari desain Figma KMID (Home/About/Activities/Impact).
object KmidColors {
    val Blue = Color(0xFF0C5EFF)        // biru utama: section, tombol, judul
    val BlueDark = Color(0xFF0B4FD9)    // kartu FAQ
    val Navy = Color(0xFF00297A)        // footer
    val NavyText = Color(0xFF1E2F6B)    // deskripsi hero
    val BlueSoft = Color(0xFFEAF1FF)
    val Orange = Color(0xFFEE9401)
    val OrangeSoft = Color(0xFFFFF3DC)
    val OrangeText = Color(0xFFD97E00)
    val Purple = Color(0xFF9333EA)
    val PurpleSoft = Color(0xFFF5EBFF)
    val Green = Color(0xFF16A34A)
    val GreenSoft = Color(0xFFE8F8EE)
    val Red = Color(0xFFE11D48)
    val Ink = Color(0xFF2B2B2B)          // judul gelap
    val Muted = Color(0xFF6B7280)
    val Line = Color(0xFFE6E9F0)
    val Placeholder = Color(0xFFE9E9E9)  // kotak gambar abu
    val SectionTint = Color(0xFFF3F6FD)  // latar section Partnership
}

private val colors = lightColorScheme(
    primary = KmidColors.Blue,
    onPrimary = Color.White,
    secondary = KmidColors.Orange,
    onSecondary = Color.White,
    tertiary = KmidColors.Navy,
    background = Color.White,
    onBackground = KmidColors.Ink,
    surface = Color.White,
    onSurface = KmidColors.Ink,
    onSurfaceVariant = KmidColors.Muted,
    outline = KmidColors.Line,
)

// Desain memakai Plus Jakarta Sans. Untuk font yang sama, tambahkan file .ttf ke res/font
// lalu set fontFamily di sini. Sementara memakai font sistem.
private val typography = Typography(
    displaySmall = TextStyle(fontSize = 30.sp, lineHeight = 36.sp, fontWeight = FontWeight.Bold),
    headlineMedium = TextStyle(fontSize = 26.sp, lineHeight = 32.sp, fontWeight = FontWeight.ExtraBold),
    headlineSmall = TextStyle(fontSize = 22.sp, lineHeight = 28.sp, fontWeight = FontWeight.Bold),
    titleLarge = TextStyle(fontSize = 18.sp, lineHeight = 24.sp, fontWeight = FontWeight.Bold),
    titleMedium = TextStyle(fontSize = 16.sp, lineHeight = 22.sp, fontWeight = FontWeight.Bold),
    titleSmall = TextStyle(fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.SemiBold),
    bodyLarge = TextStyle(fontSize = 15.sp, lineHeight = 23.sp),
    bodyMedium = TextStyle(fontSize = 14.sp, lineHeight = 21.sp),
    bodySmall = TextStyle(fontSize = 12.sp, lineHeight = 18.sp),
    labelMedium = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.4.sp),
)

@Composable
fun KmidTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = colors, typography = typography, content = content)
}
