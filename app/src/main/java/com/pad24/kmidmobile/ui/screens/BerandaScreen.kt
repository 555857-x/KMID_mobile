package com.pad24.kmidmobile.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pad24.kmidmobile.R
import com.pad24.kmidmobile.data.SampleContent
import com.pad24.kmidmobile.data.model.Achievement
import com.pad24.kmidmobile.data.model.Faq
import com.pad24.kmidmobile.data.model.HomeProgram
import com.pad24.kmidmobile.data.model.Tone
import com.pad24.kmidmobile.ui.components.CenteredHero
import com.pad24.kmidmobile.ui.components.KImageBox
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

private const val ACHIEVEMENTS_PER_PAGE = 3

/** Home: hero -> Tentang KMID (biru) -> Programs -> Achievements (paginasi) -> FAQ -> footer. */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BerandaScreen(onJoinClick: () -> Unit, onNavigate: (Screen) -> Unit) {
    var page by rememberSaveable { mutableIntStateOf(0) }
    val all = SampleContent.achievements
    val pageCount = (all.size + ACHIEVEMENTS_PER_PAGE - 1) / ACHIEVEMENTS_PER_PAGE
    val shown = all.drop(page * ACHIEVEMENTS_PER_PAGE).take(ACHIEVEMENTS_PER_PAGE)

    LazyColumn {
        item {
            CenteredHero(
                title = listOf(
                    TitlePart("Bangun Masa Depan"),
                    TitlePart("Ekosistem Digital", KmidColors.Blue),
                    TitlePart("Bersama"),
                    TitlePart("KMID", KmidColors.Blue),
                ),
                description = SampleContent.TAGLINE,
            ) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    KmidButton("Pelajari Program Kami", onClick = { onNavigate(Screen.Activities) })
                    KmidButton("Join KMID", onClick = onJoinClick, container = KmidColors.Orange)
                }
            }
        }

        item { AboutBand() }

        item {
            Spacer(Modifier.height(44.dp))
            SectionTitle("Programs")
            Spacer(Modifier.height(20.dp))
        }
        items(SampleContent.homePrograms.size) { i ->
            HomeProgramCard(SampleContent.homePrograms[i])
            Spacer(Modifier.height(12.dp))
        }

        item {
            Spacer(Modifier.height(36.dp))
            SectionTitle("Achievements")
            Spacer(Modifier.height(20.dp))
        }
        items(shown.size, key = { "ach-$page-$it" }) { i ->
            AchievementCard(shown[i])
            Spacer(Modifier.height(14.dp))
        }
        item { Pagination(pageCount, page, onChange = { page = it }) }

        faqSection()

        item {
            Spacer(Modifier.height(40.dp))
            KmidFooter(onNavigate)
        }
    }
}

@Composable
private fun AboutBand() {
    Column(
        Modifier.fillMaxWidth().background(KmidColors.Blue).padding(horizontal = 20.dp, vertical = 36.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        // Desain: kartu putih berisi foto. Ganti img_lidm dengan foto pilihan tim.
        Image(
            painterResource(R.drawable.img_lidm),
            contentDescription = "Dokumentasi KMID",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().aspectRatio(16f / 10f).floatingCard(12.dp),
        )
        Text("TENTANG KMID", style = MaterialTheme.typography.headlineMedium, color = Color.White)
        Text(SampleContent.ABOUT_SHORT, style = MaterialTheme.typography.bodyLarge, color = Color.White)
    }
}

@Composable
private fun HomeProgramCard(p: HomeProgram) {
    KmidCard(Modifier.padding(horizontal = 20.dp), padding = 12.dp) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f).padding(end = 12.dp)) {
                Text(p.title, style = MaterialTheme.typography.titleMedium, color = KmidColors.Ink)
                Spacer(Modifier.height(6.dp))
                Text(p.description, style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted, maxLines = 4)
            }
            KImageBox(Modifier.size(104.dp), iconSize = 60.dp)
        }
    }
}

@Composable
private fun AchievementCard(a: Achievement) {
    KmidCard(Modifier.padding(horizontal = 20.dp), padding = 0.dp) {
        Column {
            KImageBox(Modifier.fillMaxWidth().height(170.dp), iconSize = 110.dp)
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Tag(a.tag, Tone.ORANGE)
                Text(a.title, style = MaterialTheme.typography.titleSmall, color = KmidColors.Ink)
                Text(a.description, style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted, maxLines = 2)
            }
        }
    }
}

private fun LazyListScope.faqSection() {
    item {
        Spacer(Modifier.height(44.dp))
        SectionTitle("Frequently Asked Questions")
        Spacer(Modifier.height(20.dp))
    }
    items(SampleContent.faqs.size) { i ->
        FaqItem(SampleContent.faqs[i])
        Spacer(Modifier.height(12.dp))
    }
}

/** Kartu FAQ biru dengan tombol +, berputar jadi × saat terbuka. */
@Composable
private fun FaqItem(faq: Faq) {
    var open by rememberSaveable { mutableStateOf(false) }
    val angle by animateFloatAsState(if (open) 45f else 0f, label = "faq")
    Column(
        Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(KmidColors.BlueDark)
            .clickable { open = !open }
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(faq.question, style = MaterialTheme.typography.bodyMedium, color = Color.White, modifier = Modifier.weight(1f))
            Spacer(Modifier.width(8.dp))
            Icon(
                Icons.Filled.Add,
                contentDescription = if (open) "Tutup jawaban" else "Buka jawaban",
                tint = Color.White,
                modifier = Modifier.rotate(angle),
            )
        }
        AnimatedVisibility(open) {
            Text(
                faq.answer,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.85f),
                modifier = Modifier.padding(top = 10.dp),
            )
        }
    }
}
