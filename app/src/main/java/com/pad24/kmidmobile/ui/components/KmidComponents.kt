package com.pad24.kmidmobile.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pad24.kmidmobile.R
import com.pad24.kmidmobile.data.SampleContent
import com.pad24.kmidmobile.data.model.Stat
import com.pad24.kmidmobile.data.model.Tone
import com.pad24.kmidmobile.ui.navigation.Screen
import com.pad24.kmidmobile.ui.theme.KmidColors

/** Potongan judul. highlight = warna kata yang disorot, null = warna gelap biasa. */
data class TitlePart(val text: String, val highlight: Color? = null)

/** Pasangan warna (latar, teks) untuk chip berdasarkan Tone. */
fun toneColors(tone: Tone): Pair<Color, Color> = when (tone) {
    Tone.BLUE -> KmidColors.BlueSoft to KmidColors.Blue
    Tone.ORANGE -> KmidColors.OrangeSoft to KmidColors.OrangeText
    Tone.PURPLE -> KmidColors.PurpleSoft to KmidColors.Purple
    Tone.GREEN -> KmidColors.GreenSoft to KmidColors.Green
    Tone.RED -> Color(0xFFFFE4EA) to KmidColors.Red
    Tone.DARK -> Color(0xFF1F2937) to Color.White
}

fun toneAccent(tone: Tone): Color = when (tone) {
    Tone.ORANGE -> KmidColors.Orange
    Tone.PURPLE -> KmidColors.Purple
    Tone.GREEN -> KmidColors.Green
    Tone.RED -> KmidColors.Red
    Tone.DARK -> KmidColors.Ink
    Tone.BLUE -> KmidColors.Blue
}

// ---------------- Top bar ----------------

@Composable
fun KmidTopBar(onMenuClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .statusBarsPadding()
            .padding(start = 16.dp, end = 4.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(painterResource(R.drawable.logo_kmid), contentDescription = "KMID", modifier = Modifier.height(34.dp))
        Spacer(Modifier.weight(1f))
        IconButton(onClick = onMenuClick) {
            Icon(Icons.Filled.Menu, contentDescription = "Buka menu", tint = KmidColors.Ink)
        }
    }
}

// ---------------- Hero ----------------

/** Latar hero: putih, glow biru di atas, blob oranye buram kiri-kanan (meniru bintang oranye di desain). */
@Composable
fun HeroBackground(modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit) {
    Box(modifier.fillMaxWidth().background(Color.White)) {
        Canvas(Modifier.matchParentSize()) {
            drawCircle(
                Brush.radialGradient(
                    listOf(KmidColors.Blue.copy(alpha = 0.18f), Color.Transparent),
                    center = Offset(size.width / 2, 0f), radius = size.width * 0.7f,
                ),
                radius = size.width * 0.7f, center = Offset(size.width / 2, 0f),
            )
            val left = Offset(size.width * 0.12f, size.height * 0.28f)
            drawCircle(
                Brush.radialGradient(listOf(KmidColors.Orange.copy(alpha = 0.55f), Color.Transparent), left, size.width * 0.22f),
                radius = size.width * 0.22f, center = left,
            )
            val right = Offset(size.width * 0.9f, size.height * 0.62f)
            drawCircle(
                Brush.radialGradient(listOf(KmidColors.Orange.copy(alpha = 0.45f), Color.Transparent), right, size.width * 0.14f),
                radius = size.width * 0.14f, center = right,
            )
        }
        content()
    }
}

/** Latar hero menggunakan backgroundmobile.png khusus untuk halaman Information & Join. */
@Composable
fun DrawableHeroBackground(modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit) {
    Box(modifier.fillMaxWidth().background(Color.White)) {
        Image(
            painter = painterResource(R.drawable.backgroundmobile),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize(),
        )
        content()
    }
}

@Composable
fun ColoredTitle(
    parts: List<TitlePart>,
    modifier: Modifier = Modifier,
    style: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.displaySmall,
    align: TextAlign = TextAlign.Center,
    baseColor: Color = KmidColors.Ink,
) {
    Text(
        buildAnnotatedString {
            parts.forEachIndexed { i, p ->
                withStyle(SpanStyle(color = p.highlight ?: baseColor)) { append(p.text) }
                if (i != parts.lastIndex) append(" ")
            }
        },
        modifier = modifier,
        style = style,
        textAlign = align,
    )
}

/** Hero tengah (Home, Activities, Impact). */
@Composable
fun CenteredHero(title: List<TitlePart>, description: String, actions: @Composable () -> Unit = {}) {
    HeroBackground {
        Column(
            Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ColoredTitle(title)
            Spacer(Modifier.height(14.dp))
            Text(description, style = MaterialTheme.typography.bodyLarge, color = KmidColors.NavyText, textAlign = TextAlign.Center)
            Spacer(Modifier.height(22.dp))
            actions()
        }
    }
}

// ---------------- Section ----------------

/** Judul section: teks + garis oranye pendek. Default biru kapital seperti "PROGRAMS". */
@Composable
fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = KmidColors.Blue,
    uppercase: Boolean = true,
    subtitle: String? = null,
    subtitleColor: Color = KmidColors.Muted,
) {
    Column(modifier.fillMaxWidth().padding(horizontal = 20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            if (uppercase) text.uppercase() else text,
            style = MaterialTheme.typography.headlineMedium,
            color = color,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(6.dp))
        Box(Modifier.width(36.dp).height(3.dp).clip(RoundedCornerShape(2.dp)).background(KmidColors.Orange))
        if (subtitle != null) {
            Spacer(Modifier.height(12.dp))
            Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = subtitleColor, textAlign = TextAlign.Center)
        }
    }
}

// ---------------- Kecil-kecil ----------------

@Composable
fun Tag(text: String, tone: Tone, filled: Boolean = false, modifier: Modifier = Modifier) {
    val (bg, fg) = toneColors(tone)
    Text(
        text,
        style = MaterialTheme.typography.labelMedium,
        color = if (filled) Color.White else fg,
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(if (filled) toneAccent(tone) else bg)
            .padding(horizontal = 8.dp, vertical = 4.dp),
    )
}

@Composable
fun KmidButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    container: Color = KmidColors.Blue,
    content: Color = Color.White,
    icon: ImageVector? = null,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(46.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = container, contentColor = content),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
        }
        Text(text, fontWeight = FontWeight.SemiBold)
    }
}

/** Kotak abu dengan ikon K. Desain memakai ini sebagai placeholder gambar program/prestasi/pengurus. */
@Composable
fun KImageBox(modifier: Modifier = Modifier, iconSize: Dp = 72.dp) {
    Box(modifier.clip(RoundedCornerShape(10.dp)).background(KmidColors.Placeholder), contentAlignment = Alignment.Center) {
        Image(painterResource(R.drawable.ic_kmid_k), contentDescription = null, modifier = Modifier.size(iconSize))
    }
}

/** Ikon K kecil di kotak biru muda (pojok kanan kartu). */
@Composable
fun KBadge(size: Dp = 34.dp) {
    Box(Modifier.size(size).clip(RoundedCornerShape(8.dp)).background(KmidColors.BlueSoft), contentAlignment = Alignment.Center) {
        Image(painterResource(R.drawable.ic_kmid_k), contentDescription = null, modifier = Modifier.size(size * 0.6f))
    }
}

@Composable
fun StatBox(stat: Stat, modifier: Modifier = Modifier, background: Color = KmidColors.SectionTint) {
    Column(
        modifier.clip(RoundedCornerShape(10.dp)).background(background).padding(vertical = 14.dp, horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stat.value, style = MaterialTheme.typography.headlineMedium, color = toneAccent(stat.tone))
        Text(
            stat.label.uppercase(),
            style = MaterialTheme.typography.labelMedium,
            color = KmidColors.Muted,
            textAlign = TextAlign.Center,
        )
    }
}

/** Kartu putih dengan border tipis dan bayangan lembut. */
@Composable
fun KmidCard(modifier: Modifier = Modifier, padding: Dp = 16.dp, content: @Composable () -> Unit) {
    Card(
        modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, KmidColors.Line),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) { Box(Modifier.padding(padding)) { content() } }
}

/** Paginasi "‹ 1 2 3 ›" seperti di desain. */
@Composable
fun Pagination(pageCount: Int, current: Int, onChange: (Int) -> Unit, modifier: Modifier = Modifier) {
    if (pageCount <= 1) return
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { if (current > 0) onChange(current - 1) }, enabled = current > 0) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Sebelumnya", tint = KmidColors.Blue)
        }
        repeat(pageCount) { i ->
            val active = i == current
            Box(
                Modifier
                    .padding(horizontal = 4.dp)
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(if (active) KmidColors.Blue else Color.Transparent)
                    .clickable { onChange(i) },
                contentAlignment = Alignment.Center,
            ) {
                Text("${i + 1}", color = if (active) Color.White else KmidColors.Blue, style = MaterialTheme.typography.bodySmall)
            }
        }
        IconButton(onClick = { if (current < pageCount - 1) onChange(current + 1) }, enabled = current < pageCount - 1) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Berikutnya", tint = KmidColors.Blue)
        }
    }
}

@Composable
fun PagerDots(count: Int, current: Int) {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        repeat(count) { i ->
            Box(
                Modifier
                    .size(width = if (i == current) 18.dp else 6.dp, height = 6.dp)
                    .clip(CircleShape)
                    .background(if (i == current) KmidColors.Blue else KmidColors.Line)
            )
        }
    }
}

// ---------------- Footer ----------------

@Composable
fun KmidFooter(onNavigate: (Screen) -> Unit = {}) {
    Column(Modifier.fillMaxWidth().background(KmidColors.Navy).padding(horizontal = 24.dp, vertical = 32.dp)) {
        Image(painterResource(R.drawable.logo_kmid_white), contentDescription = "KMID", modifier = Modifier.height(40.dp))
        Spacer(Modifier.height(16.dp))
        Text(SampleContent.ADDRESS, color = Color.White, style = MaterialTheme.typography.bodySmall)
        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            // Ganti huruf dengan ikon vektor (Instagram, YouTube, X, TikTok) dari Figma.
            listOf("IG", "YT", "X", "TT").forEach {
                Box(
                    Modifier.size(32.dp).clip(RoundedCornerShape(6.dp)).background(KmidColors.Blue),
                    contentAlignment = Alignment.Center,
                ) { Text(it, color = Color.White, style = MaterialTheme.typography.labelMedium) }
            }
        }
        Spacer(Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(48.dp)) {
            val noop: () -> Unit = {}
            val pages: List<Pair<String, () -> Unit>> =
                Screen.entries.map { s -> s.label to { onNavigate(s) } } + ("Information" to noop)
            FooterColumn("Page", pages)
            FooterColumn("Support", listOf("FAQ" to noop, "Contact" to noop))
        }
        Spacer(Modifier.height(24.dp))
        HorizontalDivider(color = Color.White.copy(alpha = 0.3f))
        Spacer(Modifier.height(16.dp))
        Text(
            "© KMID. All rights reserved",
            color = Color.White,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun FooterColumn(title: String, links: List<Pair<String, () -> Unit>>) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(title, color = Color.White, style = MaterialTheme.typography.titleSmall)
        links.forEach { (label, action) ->
            Text(label, color = Color.White, style = MaterialTheme.typography.bodySmall, modifier = Modifier.clickable(onClick = action))
        }
    }
}

/** Kartu dengan bayangan lebih tebal (kartu putih di atas section biru). */
fun Modifier.floatingCard(radius: Dp = 16.dp): Modifier =
    this.shadow(8.dp, RoundedCornerShape(radius)).clip(RoundedCornerShape(radius)).background(Color.White)

fun Modifier.outlined(radius: Dp = 12.dp, color: Color = KmidColors.Line): Modifier =
    this.border(1.dp, color, RoundedCornerShape(radius))
