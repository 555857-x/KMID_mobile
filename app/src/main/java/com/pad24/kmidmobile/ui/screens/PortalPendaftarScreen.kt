package com.pad24.kmidmobile.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pad24.kmidmobile.R
import com.pad24.kmidmobile.data.SampleContent
import com.pad24.kmidmobile.data.model.InterviewSlot
import com.pad24.kmidmobile.ui.components.DrawableHeroBackground
import com.pad24.kmidmobile.ui.components.KmidButton
import com.pad24.kmidmobile.ui.components.KmidFooter
import com.pad24.kmidmobile.ui.navigation.Screen
import com.pad24.kmidmobile.ui.theme.KmidColors

/**
 * Portal Pendaftar KMID (Form Oprec & Jadwal Wawancara).
 * Versi Android / Mobile responsif yang disesuaikan dari desain Desktop 4 halaman.
 */
@Composable
fun PortalPendaftarScreen(onNavigate: (Screen) -> Unit) {
    val context = LocalContext.current
    var selectedPortalTab by remember { mutableIntStateOf(0) } // 0: Form Oprec, 1: Jadwal Wawancara

    // State Form Oprec
    var currentStep by remember { mutableIntStateOf(0) } // 0: Step A, 1: Step B, 2: Step C
    var namaLengkap by remember { mutableStateOf("") }
    var nim by remember { mutableStateOf("") }
    var asalProdi by remember { mutableStateOf("") }

    var pilihanDivisi1 by remember { mutableStateOf("Media Creative") }
    var alasanDivisi1 by remember { mutableStateOf("") }
    var pilihanDivisi2 by remember { mutableStateOf("IPDP") }
    var alasanDivisi2 by remember { mutableStateOf("") }

    var cvFileName by remember { mutableStateOf<String?>(null) }
    var portfolioFileName by remember { mutableStateOf<String?>(null) }
    var nomorWhatsapp by remember { mutableStateOf("") }

    // State Jadwal Wawancara
    var selectedTimeSlot by remember { mutableStateOf("10:15–10:30") }

    LazyColumn {
        item {
            DrawableHeroBackground {
                Column(Modifier.fillMaxWidth()) {
                    // Header Portal User Info
                    PortalUserHeader()
                    Spacer(Modifier.height(16.dp))

                    // Sub Navigation Tabs (Oprec vs Wawancara)
                    PortalNavTabs(
                        selectedTab = selectedPortalTab,
                    ) { selectedPortalTab = it }
                    Spacer(Modifier.height(20.dp))
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp),
            ) {
                if (selectedPortalTab == 0) {
                    // TAB 1: Pendaftaran Oprec Staff KMID
                    OprecFormCard(
                        currentStep = currentStep,
                        onStepChange = { currentStep = it },
                        namaLengkap = namaLengkap,
                        onNamaChange = { namaLengkap = it },
                        nim = nim,
                        onNimChange = { nim = it },
                        asalProdi = asalProdi,
                        onProdiChange = { asalProdi = it },
                        pilihanDivisi1 = pilihanDivisi1,
                        onDivisi1Change = { pilihanDivisi1 = it },
                        alasanDivisi1 = alasanDivisi1,
                        onAlasan1Change = { alasanDivisi1 = it },
                        pilihanDivisi2 = pilihanDivisi2,
                        onDivisi2Change = { pilihanDivisi2 = it },
                        alasanDivisi2 = alasanDivisi2,
                        onAlasan2Change = { alasanDivisi2 = it },
                        cvFileName = cvFileName,
                        onCvAttach = { cvFileName = "CV_AhmadTariq.pdf (1.2 MB)" },
                        portfolioFileName = portfolioFileName,
                        onPortfolioAttach = { portfolioFileName = "Portfolio_MediaCreative.pdf (4.5 MB)" },
                        nomorWhatsapp = nomorWhatsapp,
                        onWhatsappChange = { nomorWhatsapp = it },
                        onSubmit = {
                            Toast.makeText(context, "Pendaftaran Oprec Berhasil Dikirim!", Toast.LENGTH_LONG).show()
                        },
                    )
                } else {
                    // TAB 2: Pengisian Jadwal Wawancara
                    InterviewScheduleCard(
                        selectedTimeSlot = selectedTimeSlot,
                        onSlotSelect = { selectedTimeSlot = it },
                        onSubmit = {
                            Toast.makeText(context, "Jadwal Wawancara ($selectedTimeSlot) Berhasil Dikirim!", Toast.LENGTH_LONG).show()
                        },
                    )
                }
            }
        }

        item {
            Spacer(Modifier.height(32.dp))
            KmidFooter(onNavigate)
        }
    }
}

/** User Bar di bagian atas portal. */
@Composable
private fun PortalUserHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // User Profile Avatar
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(KmidColors.Blue),
            contentAlignment = Alignment.Center,
        ) {
            Text("T", style = MaterialTheme.typography.titleMedium, color = Color.White, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.width(10.dp))
        Column {
            Text("Tariq Hifzhillah", style = MaterialTheme.typography.titleSmall, color = KmidColors.Ink, fontWeight = FontWeight.Bold)
            Text("Akun mahasiswa", style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted)
        }
        Spacer(Modifier.weight(1f))
        IconButton(onClick = {}) {
            Icon(Icons.Filled.Notifications, contentDescription = "Notifikasi", tint = KmidColors.Blue)
        }
    }
}

/** Navigasi Tab (Pendaftaran Oprec vs Pengisian Jadwal Wawancara). */
@Composable
private fun PortalNavTabs(
    selectedTab: Int,
    onTabSelect: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, KmidColors.Line, RoundedCornerShape(12.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        val tab1Active = selectedTab == 0
        Row(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(if (tab1Active) KmidColors.BlueSoft else Color.Transparent)
                .clickable { onTabSelect(0) }
                .padding(vertical = 10.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                Icons.Filled.Edit,
                contentDescription = null,
                tint = if (tab1Active) KmidColors.Blue else KmidColors.Muted,
                modifier = Modifier.size(16.dp),
            )
            Spacer(Modifier.width(6.dp))
            Text(
                "Pendaftaran Oprec",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = if (tab1Active) FontWeight.Bold else FontWeight.Normal,
                color = if (tab1Active) KmidColors.Blue else KmidColors.Muted,
            )
        }

        val tab2Active = selectedTab == 1
        Row(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(if (tab2Active) KmidColors.BlueSoft else Color.Transparent)
                .clickable { onTabSelect(1) }
                .padding(vertical = 10.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                Icons.Filled.DateRange,
                contentDescription = null,
                tint = if (tab2Active) KmidColors.Blue else KmidColors.Muted,
                modifier = Modifier.size(16.dp),
            )
            Spacer(Modifier.width(6.dp))
            Text(
                "Jadwal Wawancara",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = if (tab2Active) FontWeight.Bold else FontWeight.Normal,
                color = if (tab2Active) KmidColors.Blue else KmidColors.Muted,
            )
        }
    }
}

// ==========================================
// FORM OPNE RECRUITMENT (TAB 1)
// ==========================================

@Composable
private fun OprecFormCard(
    currentStep: Int,
    onStepChange: (Int) -> Unit,
    namaLengkap: String,
    onNamaChange: (String) -> Unit,
    nim: String,
    onNimChange: (String) -> Unit,
    asalProdi: String,
    onProdiChange: (String) -> Unit,
    pilihanDivisi1: String,
    onDivisi1Change: (String) -> Unit,
    alasanDivisi1: String,
    onAlasan1Change: (String) -> Unit,
    pilihanDivisi2: String,
    onDivisi2Change: (String) -> Unit,
    alasanDivisi2: String,
    onAlasan2Change: (String) -> Unit,
    cvFileName: String?,
    onCvAttach: () -> Unit,
    portfolioFileName: String?,
    onPortfolioAttach: () -> Unit,
    nomorWhatsapp: String,
    onWhatsappChange: (String) -> Unit,
    onSubmit: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, KmidColors.Line),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            // Main Card Header
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painterResource(R.drawable.ic_kmid_k), contentDescription = null, modifier = Modifier.size(24.dp))
                Spacer(Modifier.width(8.dp))
                Text("KMID UGM", style = MaterialTheme.typography.labelMedium, color = KmidColors.Blue, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(8.dp))
            Text("Open Recruitment KMID", style = MaterialTheme.typography.headlineSmall, color = KmidColors.Ink, fontWeight = FontWeight.ExtraBold)
            Spacer(Modifier.height(4.dp))
            Box(Modifier.width(40.dp).height(3.dp).clip(RoundedCornerShape(2.dp)).background(KmidColors.Orange))
            Spacer(Modifier.height(10.dp))
            Text(
                "Halo, calon anggota KMID! Lengkapi data diri, pilihan divisi, dan berkasmu melalui formulir di bawah ini.",
                style = MaterialTheme.typography.bodySmall,
                color = KmidColors.Muted,
            )
            Spacer(Modifier.height(18.dp))

            // Step Indicator Header
            StepProgressIndicator(currentStep = currentStep, onStepClick = onStepChange)
            Spacer(Modifier.height(20.dp))
            HorizontalDivider(color = KmidColors.Line)
            Spacer(Modifier.height(20.dp))

            when (currentStep) {
                0 -> StepADataDiri(
                    namaLengkap = namaLengkap,
                    onNamaChange = onNamaChange,
                    nim = nim,
                    onNimChange = onNimChange,
                    asalProdi = asalProdi,
                    onProdiChange = onProdiChange,
                    onNext = { onStepChange(1) },
                )
                1 -> StepBPilihanDivisi(
                    pilihanDivisi1 = pilihanDivisi1,
                    onDivisi1Change = onDivisi1Change,
                    alasanDivisi1 = alasanDivisi1,
                    onAlasan1Change = onAlasan1Change,
                    pilihanDivisi2 = pilihanDivisi2,
                    onDivisi2Change = onDivisi2Change,
                    alasanDivisi2 = alasanDivisi2,
                    onAlasan2Change = onAlasan2Change,
                    onBack = { onStepChange(0) },
                    onNext = { onStepChange(2) },
                )
                2 -> StepCBerkasKontak(
                    cvFileName = cvFileName,
                    onCvAttach = onCvAttach,
                    portfolioFileName = portfolioFileName,
                    onPortfolioAttach = onPortfolioAttach,
                    nomorWhatsapp = nomorWhatsapp,
                    onWhatsappChange = onWhatsappChange,
                    onBack = { onStepChange(1) },
                    onSubmit = onSubmit,
                )
            }
        }
    }
}

/** Indikator Langkah Pengerjaan Form A -> B -> C. */
@Composable
private fun StepProgressIndicator(
    currentStep: Int,
    onStepClick: (Int) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val steps = listOf("A. Data Diri", "B. Pilihan Divisi", "C. Berkas & Kontak")
        steps.forEachIndexed { index, label ->
            val active = index == currentStep
            val done = index < currentStep
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onStepClick(index) }
                    .padding(horizontal = 6.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(
                            when {
                                done -> KmidColors.Green
                                active -> KmidColors.Orange
                                else -> KmidColors.Line
                            },
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    if (done) {
                        Icon(Icons.Filled.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                    } else {
                        Text(
                            ('A' + index).toString(),
                            style = MaterialTheme.typography.labelMedium,
                            color = if (active) Color.White else KmidColors.Muted,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
                Spacer(Modifier.width(6.dp))
                Text(
                    label,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = if (active) FontWeight.Bold else FontWeight.Normal,
                    color = if (active) KmidColors.Ink else KmidColors.Muted,
                )
            }
        }
    }
}

// ---------------- STEP A: DATA DIRI ----------------

@Composable
private fun StepADataDiri(
    namaLengkap: String,
    onNamaChange: (String) -> Unit,
    nim: String,
    onNimChange: (String) -> Unit,
    asalProdi: String,
    onProdiChange: (String) -> Unit,
    onNext: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SectionHeaderBadge(letter = "A", title = "Data diri", subtitle = "Kenalkan dirimu dengan mengisi identitas mahasiswa.")

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("1. Nama lengkap", style = MaterialTheme.typography.titleSmall, color = KmidColors.Ink, fontWeight = FontWeight.SemiBold)
            FormTextField(
                value = namaLengkap,
                onValueChange = onNamaChange,
                placeholder = "Masukkan nama lengkap",
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("2. NIM", style = MaterialTheme.typography.titleSmall, color = KmidColors.Ink, fontWeight = FontWeight.SemiBold)
            FormTextField(
                value = nim,
                onValueChange = onNimChange,
                placeholder = "Masukkan NIM",
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("3. Asal Prodi", style = MaterialTheme.typography.titleSmall, color = KmidColors.Ink, fontWeight = FontWeight.SemiBold)
            FormTextField(
                value = asalProdi,
                onValueChange = onProdiChange,
                placeholder = "Masukkan program studi",
            )
        }

        Spacer(Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            KmidButton(
                text = "Selanjutnya →",
                onClick = onNext,
                container = KmidColors.Blue,
            )
        }
    }
}

// ---------------- STEP B: PILIHAN DIVISI ----------------

@Composable
private fun StepBPilihanDivisi(
    pilihanDivisi1: String,
    onDivisi1Change: (String) -> Unit,
    alasanDivisi1: String,
    onAlasan1Change: (String) -> Unit,
    pilihanDivisi2: String,
    onDivisi2Change: (String) -> Unit,
    alasanDivisi2: String,
    onAlasan2Change: (String) -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
        SectionHeaderBadge(letter = "B", title = "Pilihan divisi", subtitle = "Tentukan pilihan pertama dan kedua, lalu ceritakan alasanmu.")

        // 4. Pilihan Divisi Pertama
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("4. Pilihan Divisi Pertama", style = MaterialTheme.typography.titleSmall, color = KmidColors.Ink, fontWeight = FontWeight.SemiBold)
            Text("Pilih satu divisi yang kamu minati.", style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted)
            DivisionRadioGroup(
                options = SampleContent.oprecDivisions,
                selected = pilihanDivisi1,
                onSelect = onDivisi1Change,
            )
        }

        // 5. Alasan Memilih Divisi Pertama
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("5. Alasan Memilih Divisi Pertama", style = MaterialTheme.typography.titleSmall, color = KmidColors.Ink, fontWeight = FontWeight.SemiBold)
            FormTextField(
                value = alasanDivisi1,
                onValueChange = onAlasan1Change,
                placeholder = "Ceritakan minat, pengalaman, dan alasanmu memilih divisi pertama.",
                singleLine = false,
                minLines = 3,
            )
        }

        HorizontalDivider(color = KmidColors.Line)

        // 6. Pilihan Kedua
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("6. Pilihan Kedua", style = MaterialTheme.typography.titleSmall, color = KmidColors.Ink, fontWeight = FontWeight.SemiBold)
            Text("Pilih satu divisi yang kamu minati.", style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted)
            DivisionRadioGroup(
                options = SampleContent.oprecDivisions,
                selected = pilihanDivisi2,
                onSelect = onDivisi2Change,
            )
        }

        // 7. Alasan Memilih Divisi Kedua
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("7. Alasan Memilih Divisi Kedua", style = MaterialTheme.typography.titleSmall, color = KmidColors.Ink, fontWeight = FontWeight.SemiBold)
            FormTextField(
                value = alasanDivisi2,
                onValueChange = onAlasan2Change,
                placeholder = "Ceritakan minat, pengalaman, dan alasanmu memilih divisi kedua.",
                singleLine = false,
                minLines = 3,
            )
        }

        Spacer(Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            KmidButton(
                text = "← Kembali",
                onClick = onBack,
                container = KmidColors.Line,
                content = KmidColors.Ink,
            )
            KmidButton(
                text = "Selanjutnya →",
                onClick = onNext,
                container = KmidColors.Blue,
            )
        }
    }
}

// ---------------- STEP C: BERKAS & KONTAK ----------------

@Composable
private fun StepCBerkasKontak(
    cvFileName: String?,
    onCvAttach: () -> Unit,
    portfolioFileName: String?,
    onPortfolioAttach: () -> Unit,
    nomorWhatsapp: String,
    onWhatsappChange: (String) -> Unit,
    onBack: () -> Unit,
    onSubmit: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
        SectionHeaderBadge(letter = "C", title = "Berkas & kontak", subtitle = "Lampirkan berkasmu dan nomor WhatsApp yang bisa dihubungi.")

        // 8. Upload CV
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("8. Upload CV", style = MaterialTheme.typography.titleSmall, color = KmidColors.Ink, fontWeight = FontWeight.SemiBold)
            FileUploadBox(
                attachedFileName = cvFileName,
                onUploadClick = onCvAttach,
            )
        }

        // 9. Upload Portofolio
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("9. Upload Portofolio", style = MaterialTheme.typography.titleSmall, color = KmidColors.Ink, fontWeight = FontWeight.SemiBold)
            Text("Dikhususkan bagi yang memilih divisi Media Creative.", style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted)
            FileUploadBox(
                attachedFileName = portfolioFileName,
                onUploadClick = onPortfolioAttach,
            )
        }

        // 10. Nomor WhatsApp
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("10. Nomor WhatsApp", style = MaterialTheme.typography.titleSmall, color = KmidColors.Ink, fontWeight = FontWeight.SemiBold)
            FormTextField(
                value = nomorWhatsapp,
                onValueChange = onWhatsappChange,
                placeholder = "Masukkan nomor WhatsApp",
            )
        }

        Text(
            "Pastikan data dan berkasmu sudah sesuai sebelum mengirim pendaftaran.",
            style = MaterialTheme.typography.bodySmall,
            color = KmidColors.Muted,
        )

        Spacer(Modifier.height(6.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            KmidButton(
                text = "← Kembali",
                onClick = onBack,
                container = KmidColors.Line,
                content = KmidColors.Ink,
            )
            KmidButton(
                text = "Kirim Pendaftaran →",
                onClick = onSubmit,
                container = KmidColors.Blue,
            )
        }
    }
}

// ==========================================
// PENGISIAN JADWAL WAWANCARA (TAB 2)
// ==========================================

@Composable
private fun InterviewScheduleCard(
    selectedTimeSlot: String,
    onSlotSelect: (String) -> Unit,
    onSubmit: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, KmidColors.Line),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
            // Header Card
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painterResource(R.drawable.ic_kmid_k), contentDescription = null, modifier = Modifier.size(24.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("KMID UGM", style = MaterialTheme.typography.labelMedium, color = KmidColors.Blue, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(8.dp))
                Text("Jadwal Wawancara", style = MaterialTheme.typography.headlineSmall, color = KmidColors.Ink, fontWeight = FontWeight.ExtraBold)
                Spacer(Modifier.height(4.dp))
                Box(Modifier.width(40.dp).height(3.dp).clip(RoundedCornerShape(2.dp)).background(KmidColors.Orange))
                Spacer(Modifier.height(10.dp))
                Text(
                    "Halo, calon anggota KMID! Tentukan Sesi Wawancaramu melalui formulir di bawah ini.",
                    style = MaterialTheme.typography.bodySmall,
                    color = KmidColors.Muted,
                )
            }

            // Data Pendaftar (Read-only Card)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(KmidColors.SectionTint)
                    .border(1.dp, KmidColors.Line, RoundedCornerShape(12.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Data pendaftar", style = MaterialTheme.typography.titleSmall, color = KmidColors.Ink, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.weight(1f))
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color.White)
                            .border(1.dp, KmidColors.Line, RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(Icons.Filled.Lock, contentDescription = null, tint = KmidColors.Muted, modifier = Modifier.size(12.dp))
                        Spacer(Modifier.width(4.dp))
                        Text("Read-only", style = MaterialTheme.typography.labelMedium, color = KmidColors.Muted)
                    }
                }

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("1. Nama Lengkap", style = MaterialTheme.typography.bodySmall, color = KmidColors.Ink, fontWeight = FontWeight.SemiBold)
                    ReadOnlyFieldBox("Ahmad Tariq Hifzhillah")
                }

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("2. Divisi yang Lolos", style = MaterialTheme.typography.bodySmall, color = KmidColors.Ink, fontWeight = FontWeight.SemiBold)
                        ReadOnlyFieldBox("Media Creative")
                    }
                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("3. Hari Wawancara", style = MaterialTheme.typography.bodySmall, color = KmidColors.Ink, fontWeight = FontWeight.SemiBold)
                        ReadOnlyFieldBox("8 Oktober – 10 Oktober")
                    }
                }
            }

            // Section 4: Jam Wawancara
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(KmidColors.OrangeSoft),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(Icons.Filled.DateRange, contentDescription = null, tint = KmidColors.OrangeText, modifier = Modifier.size(16.dp))
                    }
                    Spacer(Modifier.width(10.dp))
                    Text("4. Jam Wawancara", style = MaterialTheme.typography.titleMedium, color = KmidColors.Ink, fontWeight = FontWeight.Bold)
                }
                Text("Pilih satu sesi wawancara. Durasi setiap sesi 15 menit.", style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted)

                // Time Slots Grid
                TimeSlotGrid(
                    slots = SampleContent.interviewSlots,
                    selectedSlot = selectedTimeSlot,
                    onSlotSelect = onSlotSelect,
                )
            }

            Text(
                "Pastikan sesi yang dipilih sudah sesuai sebelum mengirim jadwal wawancara.",
                style = MaterialTheme.typography.bodySmall,
                color = KmidColors.Muted,
            )

            KmidButton(
                text = "Kirim Jadwal Wawancara →",
                onClick = onSubmit,
                modifier = Modifier.fillMaxWidth(),
                container = KmidColors.Blue,
            )
        }
    }
}

// ==========================================
// HELPER COMPONENTS
// ==========================================

@Composable
private fun SectionHeaderBadge(letter: String, title: String, subtitle: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(KmidColors.OrangeSoft),
            contentAlignment = Alignment.Center,
        ) {
            Text(letter, style = MaterialTheme.typography.titleMedium, color = KmidColors.OrangeText, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.width(12.dp))
        Column {
            Text(title, style = MaterialTheme.typography.titleMedium, color = KmidColors.Ink, fontWeight = FontWeight.Bold)
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted)
        }
    }
}

@Composable
private fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    singleLine: Boolean = true,
    minLines: Int = 1,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(placeholder, style = MaterialTheme.typography.bodyMedium, color = KmidColors.Muted) },
        singleLine = singleLine,
        minLines = minLines,
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = KmidColors.SectionTint,
            unfocusedContainerColor = KmidColors.SectionTint,
            focusedBorderColor = KmidColors.Blue,
            unfocusedBorderColor = KmidColors.Line,
        ),
    )
}

@Composable
private fun ReadOnlyFieldBox(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(1.dp, KmidColors.Line, RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
    ) {
        Text(text, style = MaterialTheme.typography.bodyMedium, color = KmidColors.Ink, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun DivisionRadioGroup(
    options: List<String>,
    selected: String,
    onSelect: (String) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        options.chunked(2).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                row.forEach { option ->
                    val active = option == selected
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (active) KmidColors.BlueSoft else Color.White)
                            .border(1.dp, if (active) KmidColors.Blue else KmidColors.Line, RoundedCornerShape(10.dp))
                            .clickable { onSelect(option) }
                            .padding(horizontal = 10.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = active,
                            onClick = { onSelect(option) },
                            colors = RadioButtonDefaults.colors(selectedColor = KmidColors.Blue, unselectedColor = KmidColors.Muted),
                            modifier = Modifier.size(20.dp),
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            option,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = if (active) FontWeight.Bold else FontWeight.Normal,
                            color = if (active) KmidColors.Blue else KmidColors.Ink,
                        )
                    }
                }
                if (row.size == 1) Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun FileUploadBox(
    attachedFileName: String?,
    onUploadClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(KmidColors.SectionTint)
            .border(1.dp, if (attachedFileName != null) KmidColors.Blue else KmidColors.Line, RoundedCornerShape(10.dp))
            .clickable(onClick = onUploadClick)
            .padding(vertical = 18.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(KmidColors.BlueSoft),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = null,
                    tint = KmidColors.Blue,
                    modifier = Modifier.size(20.dp),
                )
            }
            Spacer(Modifier.width(12.dp))
            Column {
                if (attachedFileName != null) {
                    Text("Berkas Terlampir:", style = MaterialTheme.typography.labelMedium, color = KmidColors.Green)
                    Text(attachedFileName, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = KmidColors.Ink)
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Klik untuk upload", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = KmidColors.Blue)
                        Spacer(Modifier.width(4.dp))
                        Text("atau pilih berkas", style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted)
                    }
                    Text("Belum ada berkas dipilih", style = MaterialTheme.typography.bodySmall, color = KmidColors.Muted)
                }
            }
        }
    }
}

@Composable
private fun TimeSlotGrid(
    slots: List<InterviewSlot>,
    selectedSlot: String,
    onSlotSelect: (String) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        slots.chunked(2).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                row.forEach { slot ->
                    val active = slot.timeRange == selectedSlot
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (active) KmidColors.BlueSoft else Color.White)
                            .border(1.5.dp, if (active) KmidColors.Blue else KmidColors.Line, RoundedCornerShape(10.dp))
                            .clickable { onSlotSelect(slot.timeRange) }
                            .padding(horizontal = 12.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = active,
                            onClick = { onSlotSelect(slot.timeRange) },
                            colors = RadioButtonDefaults.colors(selectedColor = KmidColors.Blue, unselectedColor = KmidColors.Muted),
                            modifier = Modifier.size(20.dp),
                        )
                        Spacer(Modifier.width(8.dp))
                        Column {
                            Text(
                                slot.timeRange,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = if (active) FontWeight.Bold else FontWeight.SemiBold,
                                color = if (active) KmidColors.Blue else KmidColors.Ink,
                            )
                            Text(
                                slot.duration,
                                style = MaterialTheme.typography.bodySmall,
                                color = KmidColors.Muted,
                            )
                        }
                    }
                }
                if (row.size == 1) Spacer(Modifier.weight(1f))
            }
        }
    }
}
