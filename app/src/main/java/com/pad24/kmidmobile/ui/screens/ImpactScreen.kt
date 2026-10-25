package com.pad24.kmidmobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pad24.kmidmobile.data.SampleContent
import com.pad24.kmidmobile.data.model.LidmWork
import com.pad24.kmidmobile.data.model.Partner
import com.pad24.kmidmobile.data.model.Tone
import com.pad24.kmidmobile.ui.components.CenteredHero
import com.pad24.kmidmobile.ui.components.KBadge
import com.pad24.kmidmobile.ui.components.KmidCard
import com.pad24.kmidmobile.ui.components.KmidFooter
import com.pad24.kmidmobile.ui.components.Pagination
import com.pad24.kmidmobile.ui.components.SectionTitle
import com.pad24.kmidmobile.ui.components.StatBox
import com.pad24.kmidmobile.ui.components.Tag
import com.pad24.kmidmobile.ui.components.TitlePart
import com.pad24.kmidmobile.ui.components.floatingCard
import com.pad24.kmidmobile.ui.components.toneAccent
import com.pad24.kmidmobile.ui.navigation.Screen
import com.pad24.kmidmobile.ui.theme.KmidColors

private const val WORKS_PER_PAGE = 3

/**
 * Impact: hero -> section biru (kartu Prestasi Tertinggi + Pusat Inkubasi LIDM)
 * -> Prestasi LIDM (paginasi) -> KMID Partnership -> footer.
 */
@Composable
fun ImpactScreen(onNavigate: (Screen) -> Unit) {
    var page by rememberSaveable { mutableIntStateOf(0) }
    val works = SampleContent.lidmWorks
    val pageCount = (works.size + WORKS_PER_PAGE - 1) / WORKS_PER_PAGE
    val shown = works.drop(page * WORKS_PER_PAGE).take(WORKS_PER_PAGE)

    LazyColumn {
        item {
            CenteredHero(
                title = listOf(
                    TitlePart("Bangun Masa Depan"),
                    TitlePart("Ekosistem Digital", KmidColors.Blue),
                    TitlePart("Bersama"),
                    TitlePart("KMID", KmidColors.Blue),
                ),
                description = SampleContent.IMPACT_TAGLINE,
            )
        }

        item { HighlightSection() }

        item {
            Spacer(Modifier.height(44.dp))
            SectionTitle("Prestasi LIDM", color = KmidColors.Ink, uppercase = false)
            Spacer(Modifier.height(20.dp))
        }
        items(shown.size, key = { "work-$page-$it" }) { i ->
            WorkCard(shown[i])
            Spacer(Modifier.height(12.dp))
        }
        item { Pagination(pageCount, page, onChange = { page = it }) }

        item {
            Spacer(Modifier.height(32.dp))
            PartnershipSection()
        }

        item { KmidFooter(onNavigate) }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun HighlightSection() {
    Column(
        Modifier.fillMaxWidth().background(KmidColors.Blue).padding(horizontal = 20.dp, vertical = 36.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp),
    ) {
        // Kartu Prestasi Tertinggi
        Column(Modifier.fillMaxWidth().floatingCard().padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Tag("PRESTASI TERTINGGI", Tone.ORANGE)
                Spacer(Modifier.weight(1f))
                Text("Puspresnas Kemdikbud", style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted)
            }
            HorizontalDivider(color = KmidColors.Line)
            Text("Juara Umum", style = MaterialTheme.typography.displaySmall, color = KmidColors.Blue)
            Text("LIDM Nasional 2024", style = MaterialTheme.typography.titleLarge, color = KmidColors.NavyText)
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SampleContent.topStats.forEach { StatBox(it, Modifier.weight(1f)) }
            }
        }

        // Pusat Inkubasi LIDM
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Pusat Inkubasi LIDM", style = MaterialTheme.typography.headlineMedium, color = Color.White)
            Text(SampleContent.INCUBATION_TEXT, style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.9f))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SampleContent.lidmDivisions.forEach { DivisionChip(it) }
            }
        }
    }
}

@Composable
private fun DivisionChip(text: String) {
    Row(
        Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White.copy(alpha = 0.08f))
            .border(1.dp, Color.White.copy(alpha = 0.35f), RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.size(5.dp).clip(CircleShape).background(Color.White))
        Spacer(Modifier.width(6.dp))
        Text(text, style = MaterialTheme.typography.bodySmall, color = Color.White)
    }
}

@Composable
private fun WorkCard(w: LidmWork) {
    KmidCard(Modifier.padding(horizontal = 20.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Tag(w.tag, Tone.ORANGE)
                Spacer(Modifier.weight(1f))
                KBadge()
            }
            Text(w.title, style = MaterialTheme.typography.titleLarge, color = KmidColors.Ink)
            Text(w.description, style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted)
        }
    }
}

@Composable
private fun PartnershipSection() {
    Column(
        Modifier.fillMaxWidth().background(KmidColors.SectionTint).padding(vertical = 40.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        SectionTitle("KMID Partnership", color = KmidColors.Ink, uppercase = false)
        Spacer(Modifier.height(8.dp))
        SampleContent.partners.chunked(2).forEach { row ->
            Row(Modifier.padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                row.forEach { PartnerCard(it, Modifier.weight(1f)) }
                if (row.size == 1) Spacer(Modifier.weight(1f))
            }
        }
    }
}

/** Kartu mitra. Desain memakai teks berwarna sebagai logo; ganti Image logo bila ada. */
@Composable
private fun PartnerCard(p: Partner, modifier: Modifier) {
    Column(
        modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFD5E0F5), RoundedCornerShape(12.dp))
            .padding(vertical = 16.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            p.name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold,
            color = toneAccent(p.tone),
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(4.dp))
        Text(p.role, style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted)
    }
}
