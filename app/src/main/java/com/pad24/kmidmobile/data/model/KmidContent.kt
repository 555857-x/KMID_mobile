package com.pad24.kmidmobile.data.model

/*
 * Model konten publik KMID. Sekarang diisi dari [com.pad24.kmidmobile.data.SampleContent]
 * (teks disalin dari desain Figma). Nanti Repository mengambil dari REST API backend PAD
 * dan cache di Room: Layar -> ViewModel -> Repository -> Room / REST API.
 */

/** Warna aksen untuk chip/label. Dipetakan ke warna nyata di UI. */
enum class Tone { BLUE, ORANGE, PURPLE, GREEN, RED, DARK }

data class Stat(val value: String, val label: String, val tone: Tone = Tone.BLUE)

/** Program di Beranda (kartu dengan gambar). */
data class HomeProgram(val title: String, val description: String)

/** Program di halaman Activities (kartu dengan kategori, jadwal, lokasi). */
data class Program(
    val title: String,
    val description: String,
    val category: String,
    val categoryTone: Tone,
    val schedule: String,
    val place: String,
    val placeTone: Tone,
)

data class Achievement(val tag: String, val title: String, val description: String)

data class Faq(val question: String, val answer: String)

data class Pillar(val title: String, val description: String, val tone: Tone)

data class HistoryItem(
    val year: String,
    val title: String,
    val description: String,
    val milestone: String,
    val tone: Tone,
    val highlighted: Boolean = false,
)

data class GalleryPhoto(val caption: String)

data class Mission(val title: String, val description: String, val tone: Tone)

data class Advisor(val badge: String, val badgeTone: Tone, val name: String, val unit: String)

data class Member(val role: String, val name: String, val study: String, val email: String? = null)

data class Agenda(
    val monthShort: String,  // "Mar", "Apr", ... untuk tab
    val day: Int,
    val monthIndex: Int,     // 1..12, untuk Intent kalender
    val year: Int,
    val title: String,
    val tag: String,
    val description: String,
    val place: String,
    val startHour: Int = 9,
    val durationHours: Int = 2,
)

data class Activity(
    val tag: String,
    val tagTone: Tone,
    val place: String?,
    val title: String,
    val description: String,
)

data class LidmWork(val tag: String, val title: String, val description: String)

data class Partner(val name: String, val role: String, val tone: Tone)
