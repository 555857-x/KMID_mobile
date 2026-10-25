package com.pad24.kmidmobile.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.compose.ui.unit.dp
import com.pad24.kmidmobile.R
import com.pad24.kmidmobile.data.SampleContent
import com.pad24.kmidmobile.data.model.Advisor
import com.pad24.kmidmobile.data.model.HistoryItem
import com.pad24.kmidmobile.data.model.Member
import com.pad24.kmidmobile.data.model.Mission
import com.pad24.kmidmobile.data.model.Pillar
import com.pad24.kmidmobile.data.model.Tone
import com.pad24.kmidmobile.ui.components.ColoredTitle
import com.pad24.kmidmobile.ui.components.HeroBackground
import com.pad24.kmidmobile.ui.components.KImageBox
import com.pad24.kmidmobile.ui.components.KmidCard
import com.pad24.kmidmobile.ui.components.KmidFooter
import com.pad24.kmidmobile.ui.components.PagerDots
import com.pad24.kmidmobile.ui.components.SectionTitle
import com.pad24.kmidmobile.ui.components.StatBox
import com.pad24.kmidmobile.ui.components.Tag
import com.pad24.kmidmobile.ui.components.TitlePart
import com.pad24.kmidmobile.ui.components.toneAccent
import com.pad24.kmidmobile.ui.components.toneColors
import com.pad24.kmidmobile.ui.navigation.Screen
import com.pad24.kmidmobile.ui.theme.KmidColors

/**
 * About: hero + foto gedung -> Riset Terapan & statistik -> Pilar Kompetensi -> Sejarah KMID
 * -> galeri -> Visi & Misi (biru) -> Struktur Kepengurusan (Pembina, BPH, Kepala Divisi) -> footer.
 */
@Composable
fun AboutScreen(onNavigate: (Screen) -> Unit) {
    LazyColumn {
        item { AboutHero() }

        item { ResearchSection() }

        item {
            Text(
                "Pilar Kompetensi Utama",
                style = MaterialTheme.typography.titleMedium,
                color = KmidColors.Ink,
                modifier = Modifier.padding(start = 20.dp, top = 28.dp, bottom = 12.dp),
            )
        }
        val pillarIcons = listOf(Icons.Filled.Create, Icons.Filled.Settings, Icons.Filled.Build, Icons.Filled.Star)
        items(SampleContent.pillars.size) { i ->
            PillarCard(SampleContent.pillars[i], pillarIcons[i % pillarIcons.size])
            Spacer(Modifier.height(10.dp))
        }

        item {
            Spacer(Modifier.height(36.dp))
            SectionTitle("Sejarah KMID")
            Spacer(Modifier.height(20.dp))
        }
        items(SampleContent.history.size) { i ->
            HistoryCard(SampleContent.history[i])
            Spacer(Modifier.height(12.dp))
        }
        item { AboutGallery() }

        item { MissionSection() }

        item {
            Spacer(Modifier.height(40.dp))
            SectionTitle("Struktur Kepengurusan")
        }
        item { GroupHeader("Pembina Komunitas", Icons.Filled.Star) }
        items(SampleContent.advisors.size) { i ->
            AdvisorCard(SampleContent.advisors[i])
            Spacer(Modifier.height(10.dp))
        }
        item { GroupHeader("Badan Pengurus Harian (BPH)", Icons.Filled.Person) }
        items(SampleContent.bph.chunked(2).size) { r ->
            MemberRow(SampleContent.bph.chunked(2)[r], filledTag = true)
        }
        item { GroupHeader("Kepala Divisi", Icons.Filled.Person) }
        items(SampleContent.divisionHeads.chunked(2).size) { r ->
            MemberRow(SampleContent.divisionHeads.chunked(2)[r], filledTag = false)
        }

        item {
            Spacer(Modifier.height(40.dp))
            KmidFooter(onNavigate)
        }
    }
}

@Composable
private fun AboutHero() {
    HeroBackground {
        Column(Modifier.fillMaxWidth().padding(top = 36.dp)) {
            Column(Modifier.padding(horizontal = 20.dp)) {
                Tag("TENTANG KMID", Tone.BLUE)
                Spacer(Modifier.height(14.dp))
                ColoredTitle(
                    listOf(TitlePart("Komunitas Mahasiswa"), TitlePart("Inovasi Digital", KmidColors.Orange)),
                    align = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(12.dp))
                Text(SampleContent.TAGLINE, style = MaterialTheme.typography.bodyLarge, color = KmidColors.NavyText)
            }
            Spacer(Modifier.height(20.dp))
            Box {
                Image(
                    painterResource(R.drawable.img_building),
                    contentDescription = "Gedung UGM",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().aspectRatio(4f / 3f),
                )
                // gradasi putih di atas foto agar menyatu dengan hero, seperti desain
                Box(
                    Modifier
                        .matchParentSize()
                        .background(Brush.verticalGradient(listOf(Color.White, Color.Transparent), endY = 220f))
                )
            }
        }
    }
}

@Composable
private fun ResearchSection() {
    Column(Modifier.padding(horizontal = 20.dp).padding(top = 32.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            "Riset Terapan &\nInkubasi Kompetisi Nasional",
            style = MaterialTheme.typography.headlineSmall,
            color = KmidColors.Ink,
        )
        val bold = "Subdirektorat Kreativitas Mahasiswa, Direktorat Kemahasiswaan UGM"
        val intro = SampleContent.ABOUT_INTRO
        val start = intro.indexOf(bold)
        Text(
            buildAnnotatedString {
                if (start < 0) {
                    append(intro)
                } else {
                    append(intro.substring(0, start))
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, color = KmidColors.Ink)) { append(bold) }
                    append(intro.substring(start + bold.length))
                }
            },
            style = MaterialTheme.typography.bodyMedium,
            color = KmidColors.Muted,
        )
        SampleContent.aboutStats.chunked(2).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                row.forEach { StatBox(it, Modifier.weight(1f)) }
            }
        }
    }
}

@Composable
private fun PillarCard(p: Pillar, icon: ImageVector) {
    val (bg, fg) = toneColors(p.tone)
    KmidCard(Modifier.padding(horizontal = 20.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(Modifier.size(30.dp).clip(RoundedCornerShape(6.dp)).background(bg), contentAlignment = Alignment.Center) {
                Icon(icon, contentDescription = null, tint = fg, modifier = Modifier.size(16.dp))
            }
            Text(p.title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = KmidColors.Ink)
            Text(p.description, style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted)
        }
    }
}

@Composable
private fun HistoryCard(h: HistoryItem) {
    KmidCard(Modifier.padding(horizontal = 20.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            val (bg, fg) = toneColors(h.tone)
            Text(
                h.year,
                style = MaterialTheme.typography.titleMedium,
                color = if (h.highlighted) Color.White else fg,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(if (h.highlighted) KmidColors.BlueDark else bg)
                    .padding(horizontal = 12.dp, vertical = 3.dp),
            )
            Text(h.title, style = MaterialTheme.typography.titleLarge, color = KmidColors.Ink)
            Text(h.description, style = MaterialTheme.typography.bodyMedium, color = KmidColors.Muted)
            Text(h.milestone, style = MaterialTheme.typography.labelMedium, color = toneAccent(h.tone))
        }
    }
}

@Composable
private fun AboutGallery() {
    val photos = SampleContent.aboutGallery
    val pager = rememberPagerState { photos.size }
    Column(Modifier.fillMaxWidth().padding(top = 12.dp, bottom = 36.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(pager, contentPadding = PaddingValues(horizontal = 28.dp), pageSpacing = 12.dp) { i ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Semua foto sementara sama (dari desain). Ganti per item.
                Image(
                    painterResource(R.drawable.img_lidm),
                    contentDescription = photos[i].caption,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().aspectRatio(2.2f).clip(RoundedCornerShape(12.dp)),
                )
                Spacer(Modifier.height(8.dp))
                Text(photos[i].caption, style = MaterialTheme.typography.bodySmall, color = KmidColors.Ink, textAlign = TextAlign.Center)
            }
        }
        Spacer(Modifier.height(10.dp))
        PagerDots(photos.size, pager.currentPage)
    }
}

@Composable
private fun MissionSection() {
    Column(
        Modifier.fillMaxWidth().background(KmidColors.Blue).padding(horizontal = 20.dp, vertical = 40.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        SectionTitle("Visi & Misi", color = Color.White)
        Spacer(Modifier.height(8.dp))
        SampleContent.missions.forEachIndexed { i, m -> MissionCard(i + 1, m) }
    }
}

@Composable
private fun MissionCard(number: Int, m: Mission) {
    val (bg, fg) = toneColors(m.tone)
    Row(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color.White).padding(16.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Box(Modifier.size(36.dp).clip(RoundedCornerShape(8.dp)).background(bg), contentAlignment = Alignment.Center) {
            Text("%02d".format(number), style = MaterialTheme.typography.titleMedium, color = fg)
        }
        Spacer(Modifier.width(12.dp))
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(m.title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = KmidColors.Ink)
            Text(m.description, style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted)
        }
    }
}

@Composable
private fun GroupHeader(text: String, icon: ImageVector) {
    Row(
        Modifier.padding(start = 20.dp, end = 20.dp, top = 28.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(icon, contentDescription = null, tint = KmidColors.Blue, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.titleLarge, color = KmidColors.Ink)
    }
}

@Composable
private fun AdvisorCard(a: Advisor) {
    KmidCard(Modifier.padding(horizontal = 20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            KImageBox(Modifier.size(56.dp).clip(CircleShape), iconSize = 30.dp)
            Spacer(Modifier.width(14.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Tag(a.badge, a.badgeTone)
                Text(a.name, style = MaterialTheme.typography.titleMedium, color = KmidColors.Ink)
                Text(a.unit, style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted)
            }
        }
    }
}

@Composable
private fun MemberRow(members: List<Member>, filledTag: Boolean) {
    Row(
        Modifier.padding(horizontal = 20.dp).padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        members.forEach { MemberCard(it, filledTag, Modifier.weight(1f)) }
        if (members.size == 1) Spacer(Modifier.weight(1f))
    }
}

/** Kartu pengurus: foto (sementara kotak K), jabatan, nama, prodi, email. */
@Composable
private fun MemberCard(m: Member, filledTag: Boolean, modifier: Modifier) {
    KmidCard(modifier, padding = 10.dp) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            KImageBox(Modifier.fillMaxWidth().aspectRatio(1.25f), iconSize = 64.dp)
            Tag(m.role, Tone.BLUE, filled = filledTag)
            Text(m.name, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = KmidColors.Ink)
            Text(m.study, style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted)
            if (m.email != null) {
                HorizontalDivider(color = KmidColors.Line)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Email, contentDescription = null, tint = KmidColors.Muted, modifier = Modifier.size(14.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(m.email, style = MaterialTheme.typography.bodySmall, color = KmidColors.Ink, maxLines = 1)
                }
            }
        }
    }
}
