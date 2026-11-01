package com.pad24.kmidmobile.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
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
import com.pad24.kmidmobile.data.model.InfoKompetisi
import com.pad24.kmidmobile.data.model.Tone
import com.pad24.kmidmobile.ui.components.ColoredTitle
import com.pad24.kmidmobile.ui.components.DrawableHeroBackground
import com.pad24.kmidmobile.ui.components.KBadge
import com.pad24.kmidmobile.ui.components.KmidButton
import com.pad24.kmidmobile.ui.components.KmidCard
import com.pad24.kmidmobile.ui.components.KmidFooter
import com.pad24.kmidmobile.ui.components.Pagination
import com.pad24.kmidmobile.ui.components.SectionTitle
import com.pad24.kmidmobile.ui.components.Tag
import com.pad24.kmidmobile.ui.components.TitlePart
import com.pad24.kmidmobile.ui.components.floatingCard
import com.pad24.kmidmobile.ui.navigation.Screen
import com.pad24.kmidmobile.ui.theme.KmidColors

/**
 * Halaman Information (gambar pertama):
 * Hero -> Open Recruitment & Pengumuman Resmi (biru) -> Info Kompetisi Nasional (paginasi) -> Footer.
 */
@Composable
fun InformationScreen(onNavigate: (Screen) -> Unit, onJoinClick: () -> Unit) {
    var page by rememberSaveable { mutableIntStateOf(1) } // Halaman 2 aktif sesuai desain
    val competitions = SampleContent.nationalCompetitions
    val pageCount = competitions.size
    val currentComp = competitions.getOrElse(page) { competitions.first() }

    LazyColumn {
        item { InformationHero() }

        item { OpenRecruitmentSection(onJoinClick = onJoinClick) }

        item {
            Spacer(Modifier.height(44.dp))
            SectionTitle("Info Kompetisi Nasional", color = KmidColors.Blue, uppercase = false)
            Spacer(Modifier.height(20.dp))
        }

        item {
            CompetitionCard(currentComp, onJoinClick = onJoinClick)
            Spacer(Modifier.height(20.dp))
            Pagination(pageCount, page, onChange = { page = it })
        }

        item {
            Spacer(Modifier.height(40.dp))
            KmidFooter(onNavigate)
        }
    }
}

@Composable
private fun InformationHero() {
    DrawableHeroBackground {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 44.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "Information",
                style = MaterialTheme.typography.labelMedium,
                color = KmidColors.Blue,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(KmidColors.BlueSoft)
                    .padding(horizontal = 16.dp, vertical = 6.dp),
            )
            Spacer(Modifier.height(18.dp))
            ColoredTitle(
                listOf(
                    TitlePart("Pusat", KmidColors.Ink),
                    TitlePart("Informasi", KmidColors.Blue),
                    TitlePart("Inovasi", KmidColors.Blue),
                    TitlePart("Digital", KmidColors.Ink),
                    TitlePart("KMID", KmidColors.Orange),
                    TitlePart("UGM", KmidColors.Ink),
                ),
            )
            Spacer(Modifier.height(14.dp))
            Text(
                SampleContent.INFORMATION_SUBTITLE,
                style = MaterialTheme.typography.bodyLarge,
                color = KmidColors.NavyText,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun OpenRecruitmentSection(onJoinClick: () -> Unit) {
    val oprec = SampleContent.oprecInfo
    Column(
        Modifier
            .fillMaxWidth()
            .background(KmidColors.Blue)
            .padding(horizontal = 20.dp, vertical = 36.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp),
    ) {
        // Kartu floating Open Recruitment
        Column(
            Modifier
                .fillMaxWidth()
                .floatingCard(16.dp)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                "REKRUTMEN PENDAFTARAN",
                style = MaterialTheme.typography.labelMedium,
                color = KmidColors.Blue,
                fontWeight = FontWeight.Bold,
            )
            Text(
                "Deadline: ${oprec.deadline}",
                style = MaterialTheme.typography.labelMedium,
                color = KmidColors.OrangeText,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(KmidColors.OrangeSoft)
                    .padding(horizontal = 10.dp, vertical = 5.dp),
            )
            Text(
                oprec.title,
                style = MaterialTheme.typography.headlineSmall,
                color = KmidColors.Ink,
            )
            Text(
                oprec.description,
                style = MaterialTheme.typography.bodyMedium,
                color = KmidColors.Muted,
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                oprec.departments.forEach { dept ->
                    Tag(dept, Tone.BLUE)
                }
            }
            Text(
                oprec.target,
                style = MaterialTheme.typography.bodySmall,
                color = KmidColors.Muted,
            )
            Spacer(Modifier.height(4.dp))
            KmidButton(
                text = "Daftar Sekarang ↗",
                onClick = onJoinClick,
                modifier = Modifier.fillMaxWidth(),
                container = KmidColors.Blue,
            )
        }

        // Pengumuman Resmi
        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Text(
                "Pengumuman Resmi",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
            )
            Text(
                "Pusat rilis informasi resmi KMID dan Subdirektorat Kreativitas Mahasiswa UGM. Alur pendaftaran kontingen lomba, fasilitasi pendanaan karya, dan surat tugas delegasi kampus dikomunikasikan secara terpadu di sini.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.9f),
            )
            Spacer(Modifier.height(4.dp))
            SampleContent.pengumumanPoints.forEach { point ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        Modifier
                            .size(24.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(KmidColors.Orange),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            Icons.Filled.Check,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(15.dp),
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    Text(
                        point,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
private fun CompetitionCard(comp: InfoKompetisi, onJoinClick: () -> Unit) {
    KmidCard(Modifier.padding(horizontal = 20.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Tag(comp.tag, Tone.ORANGE)
                Spacer(Modifier.weight(1f))
                KBadge()
            }
            Text(
                comp.title,
                style = MaterialTheme.typography.titleLarge,
                color = KmidColors.Ink,
            )
            Text(
                comp.description,
                style = MaterialTheme.typography.bodyMedium,
                color = KmidColors.Muted,
            )
            Spacer(Modifier.height(4.dp))
            KmidButton(
                text = "Lihat Selengkapnya ↗",
                onClick = onJoinClick,
                modifier = Modifier.fillMaxWidth(),
                container = KmidColors.Blue,
            )
        }
    }
}
