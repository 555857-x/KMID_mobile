# KMID Mobile (PAD 24)

Aplikasi Android Jetpack Compose untuk 4 layar publik: Beranda, About, Activities, Impact.
Navigasi lewat menu hamburger di `MainActivity` (sesuai slide "7 Activity").

## Cara buka
1. Android Studio (Ladybug atau lebih baru) -> File -> Open -> pilih folder `kmid-mobile`.
2. Tunggu Gradle sync (Android Studio akan mengunduh Gradle 8.11.1, AGP 8.7.3, Kotlin 2.0.21).
3. Run di emulator/HP, minSdk 26.

## Struktur
```
app/src/main/java/com/pad24/kmidmobile/
  MainActivity.kt              drawer + NavHost
  data/model/KmidContent.kt    model data
  data/Sa mpleContent.kt        DATA CONTOH, ganti dengan isi asli / API
  ui/theme/Theme.kt            warna & tipografi
  ui/components/               TopBar, Hero, SectionHeader, StatCard, Chip, Footer
  ui/navigation/KmidNav.kt     daftar menu + isi drawer
  ui/screens/                  BerandaScreen, AboutScreen, ActivitiesScreen, ImpactScreen
```

## Aset
`res/drawable-nodpi/` berisi logo, ikon K, foto gedung, dan foto LIDM yang dipotong dari ekspor PNG Figma.
Untuk kualitas terbaik, ekspor ulang langsung dari Figma (SVG/PNG 3x) dengan nama file yang sama.

## Yang perlu disesuaikan
- Font: desain memakai Plus Jakarta Sans. Taruh .ttf di `res/font` lalu set di `ui/theme/Theme.kt`.
- Ikon sosmed footer masih huruf (IG/YT/X/TT). Ganti dengan vektor dari Figma.
- Jawaban FAQ belum ada di desain. Isi di `SampleContent.faqs`.
- Agenda selain bulan Mei, nama pengurus, dan kartu Prestasi LIDM masih dummy dari desain.
- Tombol Join KMID masih Toast; nanti arahkan ke LoginActivity / JoinKmidActivity.
- Tahap berikut: ViewModel + Repository (Room cache + Retrofit) sesuai slide arsitektur.
