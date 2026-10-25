package com.pad24.kmidmobile.ui.screens

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pad24.kmidmobile.R
import com.pad24.kmidmobile.data.SampleContent
import com.pad24.kmidmobile.data.model.Activity
import com.pad24.kmidmobile.data.model.Agenda
import com.pad24.kmidmobile.data.model.Program
import com.pad24.kmidmobile.data.model.Tone
import com.pad24.kmidmobile.ui.components.CenteredHero
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
import java.util.Calendar

private const val GALLERY_PER_PAGE = 2

/**
 * Activities: hero -> Programs -> Linimasa Agenda (tab Mar–Jun, simpan ke Google Calendar)
 * -> Galeri Aktivitas & Dokumentasi (paginasi) -> footer.
 */
@Composable
fun ActivitiesScreen(onNavigate: (Screen) -> Unit) {
    val context = LocalContext.current
    var month by rememberSaveable { mutableStateOf("Mei") }
    var page by rememberSaveable { mutableIntStateOf(0) }
    val agendas = SampleContent.agendas.filter { it.monthShort == month }
    val gallery = SampleContent.activities
    val pageCount = (gallery.size + GALLERY_PER_PAGE - 1) / GALLERY_PER_PAGE
    val shown = gallery.drop(page * GALLERY_PER_PAGE).take(GALLERY_PER_PAGE)

    LazyColumn {
        item {
            CenteredHero(
                title = listOf(
                    TitlePart("Rangkaian Aktivitas dan Kurikulum"),
                    TitlePart("KMID", KmidColors.Blue),
                    TitlePart("UGM", KmidColors.Orange),
                ),
                description = SampleContent.TAGLINE,
            )
        }

        item {
            Spacer(Modifier.height(20.dp))
            SectionTitle("Programs", subtitle = SampleContent.PROGRAMS_INTRO)
            Spacer(Modifier.height(20.dp))
        }
        items(SampleContent.programs.size) { i ->
            ProgramCard(SampleContent.programs[i])
            Spacer(Modifier.height(14.dp))
        }

        item {
            Spacer(Modifier.height(28.dp))
            TimelineSection(
                month = month,
                agendas = agendas,
                onMonthChange = { month = it },
                onSave = { addToCalendar(context, it) },
            )
        }

        item {
            Column(Modifier.padding(horizontal = 20.dp, vertical = 36.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Galeri Aktivitas & Dokumentasi Mahasiswa", style = MaterialTheme.typography.headlineMedium, color = KmidColors.Ink)
                Text(SampleContent.GALLERY_INTRO, style = MaterialTheme.typography.bodyMedium, color = KmidColors.Muted)
            }
        }
        items(shown.size, key = { "act-$page-$it" }) { i ->
            ActivityPhotoCard(shown[i])
            Spacer(Modifier.height(14.dp))
        }
        item { Pagination(pageCount, page, onChange = { page = it }) }

        item {
            Spacer(Modifier.height(32.dp))
            KmidFooter(onNavigate)
        }
    }
}

@Composable
private fun ProgramCard(p: Program) {
    KmidCard(Modifier.padding(horizontal = 20.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Tag(p.category, p.categoryTone)
                Spacer(Modifier.weight(1f))
                KBadge()
            }
            Text(p.title, style = MaterialTheme.typography.titleLarge, color = KmidColors.Ink)
            Text(p.description, style = MaterialTheme.typography.bodyMedium, color = KmidColors.Muted)
            HorizontalDivider(color = KmidColors.Line)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(8.dp).clip(CircleShape).background(KmidColors.Orange))
                Spacer(Modifier.width(6.dp))
                Text(p.schedule, style = MaterialTheme.typography.bodySmall, color = KmidColors.Ink)
                Spacer(Modifier.width(10.dp))
                Tag(p.place, p.placeTone)
            }
        }
    }
}

@Composable
private fun TimelineSection(
    month: String,
    agendas: List<Agenda>,
    onMonthChange: (String) -> Unit,
    onSave: (Agenda) -> Unit,
) {
    Column(
        Modifier.fillMaxWidth().background(KmidColors.Blue).padding(horizontal = 20.dp, vertical = 36.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        // Kartu linimasa
        Column(Modifier.fillMaxWidth().floatingCard().padding(18.dp)) {
            Text("LINIMASA AGENDA", style = MaterialTheme.typography.labelMedium, color = KmidColors.Blue)
            Spacer(Modifier.height(4.dp))
            Text("Maret — Juni 2025", style = MaterialTheme.typography.titleLarge, color = KmidColors.Ink)
            Spacer(Modifier.height(12.dp))
            MonthTabs(SampleContent.agendaMonths, month, onMonthChange)
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(color = KmidColors.Line)
            if (agendas.isEmpty()) {
                Text(
                    "Belum ada agenda di bulan ini.",
                    style = MaterialTheme.typography.bodySmall,
                    color = KmidColors.Muted,
                    modifier = Modifier.padding(vertical = 16.dp),
                )
            }
            agendas.forEach { AgendaRow(it, onSave = { onSave(it) }) }
        }

        // Teks ajakan pengingat
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(SampleContent.REMINDER_TITLE, style = MaterialTheme.typography.headlineMedium, color = Color.White)
            Text(SampleContent.REMINDER_TEXT, style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.9f))
            SampleContent.reminderPoints.forEach { point ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(20.dp).clip(CircleShape).background(KmidColors.Orange), contentAlignment = Alignment.Center) {
                        Icon(Icons.Filled.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(13.dp))
                    }
                    Spacer(Modifier.width(10.dp))
                    Text(point, style = MaterialTheme.typography.bodyMedium, color = Color.White)
                }
            }
            Spacer(Modifier.height(4.dp))
            KmidButton(
                "Simpan ke Google Calendar",
                onClick = { agendas.firstOrNull()?.let(onSave) },
                container = Color.White,
                content = KmidColors.Blue,
                icon = Icons.Filled.DateRange,
            )
        }
    }
}

/** Tab segmen Mar / Apr / Mei / Jun. */
@Composable
private fun MonthTabs(months: List<String>, selected: String, onSelect: (String) -> Unit) {
    Row(
        Modifier.clip(RoundedCornerShape(8.dp)).background(KmidColors.SectionTint).padding(3.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        months.forEach { m ->
            val active = m == selected
            Text(
                m,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = if (active) FontWeight.Bold else FontWeight.Normal,
                color = if (active) Color.White else KmidColors.Ink,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (active) KmidColors.Blue else Color.Transparent)
                    .clickable { onSelect(m) }
                    .padding(horizontal = 14.dp, vertical = 6.dp),
            )
        }
    }
}

@Composable
private fun AgendaRow(a: Agenda, onSave: () -> Unit) {
    Row(Modifier.fillMaxWidth().padding(vertical = 14.dp), verticalAlignment = Alignment.Top) {
        Column(
            Modifier.clip(RoundedCornerShape(8.dp)).background(KmidColors.PurpleSoft).padding(horizontal = 10.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(a.monthShort.uppercase(), style = MaterialTheme.typography.labelMedium, color = KmidColors.Purple)
            Text("${a.day}", style = MaterialTheme.typography.titleLarge, color = KmidColors.Purple)
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Tag(a.tag, Tone.PURPLE)
            Text(a.title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = KmidColors.Ink)
            Text(a.description, style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Place, contentDescription = null, tint = KmidColors.Red, modifier = Modifier.size(12.dp))
                Spacer(Modifier.width(2.dp))
                Text(a.place, style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted)
            }
        }
        IconButton(onClick = onSave) {
            Icon(Icons.Filled.DateRange, contentDescription = "Simpan ${a.title} ke kalender", tint = KmidColors.Blue)
        }
    }
}

/** Kartu foto dengan gradasi gelap, tag, lokasi, judul, deskripsi. */
@Composable
private fun ActivityPhotoCard(a: Activity) {
    Box(Modifier.padding(horizontal = 20.dp).fillMaxWidth().height(260.dp).clip(RoundedCornerShape(16.dp))) {
        // Foto sementara sama untuk semua kartu. Ganti per kegiatan.
        Image(
            painterResource(R.drawable.img_lidm),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize(),
        )
        Box(
            Modifier.matchParentSize().background(
                Brush.verticalGradient(listOf(Color.Transparent, Color(0xE6000814)), startY = 120f)
            )
        )
        Column(
            Modifier.align(Alignment.BottomStart).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Tag(a.tag, a.tagTone, filled = a.tagTone != Tone.DARK)
                if (a.place != null) {
                    Spacer(Modifier.width(8.dp))
                    Text(a.place, style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.85f), maxLines = 1)
                }
            }
            Text(a.title, style = MaterialTheme.typography.titleLarge, color = Color.White)
            Text(a.description, style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.85f))
        }
    }
}

/** Buka aplikasi kalender (Google Calendar) dengan form event yang sudah terisi. */
private fun addToCalendar(context: Context, agenda: Agenda) {
    val start = Calendar.getInstance().apply {
        set(agenda.year, agenda.monthIndex - 1, agenda.day, agenda.startHour, 0, 0)
    }
    val end = (start.clone() as Calendar).apply { add(Calendar.HOUR_OF_DAY, agenda.durationHours) }
    val intent = Intent(Intent.ACTION_INSERT)
        .setData(CalendarContract.Events.CONTENT_URI)
        .putExtra(CalendarContract.Events.TITLE, "KMID: ${agenda.title}")
        .putExtra(CalendarContract.Events.DESCRIPTION, agenda.description)
        .putExtra(CalendarContract.Events.EVENT_LOCATION, agenda.place)
        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, start.timeInMillis)
        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end.timeInMillis)
    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "Aplikasi kalender tidak ditemukan", Toast.LENGTH_SHORT).show()
    }
}
