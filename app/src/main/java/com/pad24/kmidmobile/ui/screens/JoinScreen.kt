package com.pad24.kmidmobile.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pad24.kmidmobile.data.SampleContent
import com.pad24.kmidmobile.data.model.DivisionRequirement
import com.pad24.kmidmobile.data.model.SelectionStep
import com.pad24.kmidmobile.data.model.Tone
import com.pad24.kmidmobile.ui.components.ColoredTitle
import com.pad24.kmidmobile.ui.components.DrawableHeroBackground
import com.pad24.kmidmobile.ui.components.KmidButton
import com.pad24.kmidmobile.ui.components.KmidFooter
import com.pad24.kmidmobile.ui.components.SectionTitle
import com.pad24.kmidmobile.ui.components.StatBox
import com.pad24.kmidmobile.ui.components.TitlePart
import com.pad24.kmidmobile.ui.components.toneAccent
import com.pad24.kmidmobile.ui.navigation.Screen
import com.pad24.kmidmobile.ui.theme.KmidColors

/**
 * Halaman Join KMID / Rekrutmen (gambar kedua):
 * Hero -> Stat Cards -> Timeline & Alur Seleksi -> Persyaratan Calon Anggota -> Footer.
 */
@Composable
fun JoinScreen(onNavigate: (Screen) -> Unit) {
    val context = LocalContext.current
    var selectedDivisionName by remember { mutableStateOf("Media Creative") }
    val divisions = SampleContent.divisionRequirements
    val currentDivision = divisions.find { it.divisionName == selectedDivisionName } ?: divisions.first()

    val onApplyClick = {
        Toast.makeText(context, "Membuka formulir pendaftaran KMID...", Toast.LENGTH_SHORT).show()
    }

    LazyColumn {
        item {
            JoinHero(onApplyClick = onApplyClick)
        }

        item {
            Spacer(Modifier.height(36.dp))
            SectionTitle(
                text = "Timeline & Alur Seleksi",
                color = KmidColors.Blue,
                uppercase = false,
                subtitle = "Perhatikan baik-baik tiap tanggal penting agar proses pendaftaran Anda berjalan lancar tanpa kendala.",
            )
            Spacer(Modifier.height(20.dp))
        }

        items(SampleContent.timelineSteps.size) { i ->
            TimelineCard(SampleContent.timelineSteps[i])
            Spacer(Modifier.height(14.dp))
        }

        item {
            Spacer(Modifier.height(32.dp))
            SectionTitle(
                text = "Persyaratan Calon Anggota",
                color = KmidColors.Blue,
                uppercase = false,
                subtitle = "Pilih tab divisi di bawah untuk melihat persyaratan tiap divisi",
            )
            Spacer(Modifier.height(18.dp))
            DivisionTabs(
                divisions = divisions.map { it.divisionName },
                selected = selectedDivisionName,
                onSelect = { selectedDivisionName = it },
            )
            Spacer(Modifier.height(16.dp))
            RequirementCard(currentDivision)
        }

        item {
            Spacer(Modifier.height(40.dp))
            KmidFooter(onNavigate)
        }
    }
}

@Composable
private fun JoinHero(onApplyClick: () -> Unit) {
    DrawableHeroBackground {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 44.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ColoredTitle(
                listOf(
                    TitlePart("Bangun Masa Depan", KmidColors.Ink),
                    TitlePart("Inovasi", KmidColors.Blue),
                    TitlePart("Digital", KmidColors.Blue),
                    TitlePart("Bersama", KmidColors.Ink),
                    TitlePart("KMID", KmidColors.Orange),
                )
            )
            Spacer(Modifier.height(14.dp))
            Text(
                SampleContent.INFORMATION_SUBTITLE,
                style = MaterialTheme.typography.bodyLarge,
                color = KmidColors.NavyText,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(22.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                KmidButton(
                    text = "Daftar Sekarang ↗",
                    onClick = onApplyClick,
                    modifier = Modifier.fillMaxWidth(),
                    container = KmidColors.Orange,
                )
                KmidButton(
                    text = "Lihat Alur & Persyaratan ↗",
                    onClick = { /* Scroll down / action */ },
                    modifier = Modifier.fillMaxWidth(),
                    container = KmidColors.Blue,
                )
            }
            Spacer(Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                SampleContent.joinStats.forEach { stat ->
                    StatBox(stat, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun TimelineCard(step: SelectionStep) {
    val borderColor = if (step.isOrange) KmidColors.Orange else KmidColors.Line
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(if (step.isOrange) 1.5.dp else 1.dp, borderColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Number pill 01, 02, 03
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (step.isOrange) KmidColors.Orange else KmidColors.BlueSoft)
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        step.stepNumber,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (step.isOrange) Color.White else KmidColors.Blue,
                    )
                }
                Spacer(Modifier.width(12.dp))
                Text(
                    step.dateRange,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = toneAccent(step.dateColor),
                )
            }
            Text(
                step.title,
                style = MaterialTheme.typography.titleLarge,
                color = KmidColors.Ink,
            )
            Text(
                step.description,
                style = MaterialTheme.typography.bodyMedium,
                color = KmidColors.Muted,
            )
            Text(
                step.tag,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = when (step.dateColor) {
                    Tone.ORANGE -> KmidColors.OrangeText
                    Tone.GREEN -> KmidColors.Green
                    else -> KmidColors.Muted
                },
            )
        }
    }
}

@Composable
private fun DivisionTabs(
    divisions: List<String>,
    selected: String,
    onSelect: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        divisions.forEach { name ->
            val active = name == selected
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (active) KmidColors.Blue else KmidColors.SectionTint)
                    .clickable { onSelect(name) }
                    .padding(horizontal = 14.dp, vertical = 10.dp),
            ) {
                Text(
                    name,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = if (active) FontWeight.Bold else FontWeight.Normal,
                    color = if (active) Color.White else KmidColors.Ink,
                )
            }
        }
    }
}

@Composable
private fun RequirementCard(req: DivisionRequirement) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, KmidColors.Line),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Text(
                req.divisionName,
                style = MaterialTheme.typography.headlineSmall,
                color = KmidColors.Ink,
            )
            Text(
                req.description,
                style = MaterialTheme.typography.bodyMedium,
                color = KmidColors.Muted,
            )
            Spacer(Modifier.height(2.dp))
            req.requirements.forEach { text ->
                Row(
                    verticalAlignment = Alignment.Top,
                ) {
                    Box(
                        modifier = Modifier
                            .size(22.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(KmidColors.BlueSoft),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            Icons.Filled.Check,
                            contentDescription = null,
                            tint = KmidColors.Blue,
                            modifier = Modifier.size(14.dp),
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text,
                        style = MaterialTheme.typography.bodyMedium,
                        color = KmidColors.Muted,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}
