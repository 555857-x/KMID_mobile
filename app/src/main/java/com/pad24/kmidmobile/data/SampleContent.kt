package com.pad24.kmidmobile.data

import com.pad24.kmidmobile.data.model.Achievement
import com.pad24.kmidmobile.data.model.Activity
import com.pad24.kmidmobile.data.model.Advisor
import com.pad24.kmidmobile.data.model.Agenda
import com.pad24.kmidmobile.data.model.DivisionRequirement
import com.pad24.kmidmobile.data.model.Faq
import com.pad24.kmidmobile.data.model.GalleryPhoto
import com.pad24.kmidmobile.data.model.HistoryItem
import com.pad24.kmidmobile.data.model.HomeProgram
import com.pad24.kmidmobile.data.model.InfoKompetisi
import com.pad24.kmidmobile.data.model.LidmWork
import com.pad24.kmidmobile.data.model.Member
import com.pad24.kmidmobile.data.model.Mission
import com.pad24.kmidmobile.data.model.OprecInfo
import com.pad24.kmidmobile.data.model.Partner
import com.pad24.kmidmobile.data.model.Pillar
import com.pad24.kmidmobile.data.model.Program
import com.pad24.kmidmobile.data.model.SelectionStep
import com.pad24.kmidmobile.data.model.Stat
import com.pad24.kmidmobile.data.model.Tone

/**
 * Konten statis yang disalin dari desain Figma.
 * Teks "Lorem ipsum", nama pengurus, dan data yang ditandai CONTOH masih dummy dari desain.
 */
object SampleContent {

    private const val LOREM =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."

    const val TAGLINE =
        "Wadah kolaborasi mahasiswa UGM untuk belajar, berinovasi, dan mengembangkan ide inovasi digital yang aplikatif."

    const val ADDRESS =
        "Bulaksumur, Caturtunggal, Kapanewon Depok, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55281."

    // ---------- HOME ----------
    const val ABOUT_SHORT =
        "Wadah kolaborasi mahasiswa UGM untuk belajar, berinovasi, dan menciptakan solusi teknologi yang berdampak."

    val homePrograms = listOf(
        HomeProgram("Seminar", LOREM),
        HomeProgram("Networking & Softskill", LOREM),
        HomeProgram("Digital Bootcamp & Mentoring", LOREM),
        HomeProgram("Persiapan Lomba & Delegasi Kampus", LOREM),
        HomeProgram("Innovation Showcase", LOREM),
    )

    val achievements = listOf(
        Achievement("LIDM 2024", "Medali Emas – Divisi Inovasi Pembelajaran Digital (IPDP)", LOREM),
        Achievement("LIDM 2025", "Juara Favorit – Divisi Poster Digital Pendidikan (PDP)", LOREM),
        Achievement("LIDM 2025", "Medali Perak – Divisi Microteaching Digital (MD)", LOREM),
        // CONTOH halaman 2
        Achievement("LIDM 2024", "Medali Emas – Divisi Inovasi Pembelajaran Digital (IPDP)", LOREM),
        Achievement("LIDM 2025", "Juara Favorit – Divisi Poster Digital Pendidikan (PDP)", LOREM),
        Achievement("LIDM 2025", "Medali Perak – Divisi Microteaching Digital (MD)", LOREM),
    )

    // Jawaban belum ada di desain: isi dengan jawaban resmi KMID.
    val faqs = listOf(
        Faq("Apa itu KMID UGM dan apa fokus utamanya?", "Jawaban menyusul dari pengurus KMID."),
        Faq("Apakah mahasiswa baru (Maba) bisa langsung mendaftar?", "Jawaban menyusul dari pengurus KMID."),
        Faq("Siapa saja yang bisa bergabung dengan KMID?", "Jawaban menyusul dari pengurus KMID."),
        Faq("Bagaimana alur seleksi anggota baru KMID?", "Jawaban menyusul dari pengurus KMID."),
        Faq("Bagaimana cara mendaftar menjadi anggota KMID?", "Jawaban menyusul dari pengurus KMID."),
        Faq("Apakah KMID hanya berfokus pada lomba LIDM saja?", "Jawaban menyusul dari pengurus KMID."),
    )

    // ---------- ABOUT ----------
    const val ABOUT_INTRO =
        "Komunitas Mahasiswa Inovasi Digital (KMID) UGM merupakan entitas binaan resmi di bawah naungan " +
            "Subdirektorat Kreativitas Mahasiswa, Direktorat Kemahasiswaan UGM. Kami memposisikan diri sebagai " +
            "jembatan interdisipliner antara mahasiswa rumpun Sains & Teknologi, Sosio-Humaniora, serta Agro-Kesehatan."

    val aboutStats = listOf(
        Stat("120+", "Mahasiswa Aktif", Tone.BLUE),
        Stat("18", "Fakultas & SV", Tone.ORANGE),
        Stat("5", "Divisi Fungsional", Tone.BLUE),
        Stat("100%", "Dukungan Mentoring", Tone.ORANGE),
    )

    val pillars = listOf(
        Pillar(
            "UI/UX & Product Design",
            "Eksplorasi Design Thinking, usability testing empiris, perancangan antarmuka aksesibel, dan design system standar industri global.",
            Tone.BLUE,
        ),
        Pillar(
            "Software & Cloud Architecture",
            "Pengembangan web adaptif, backend scalable, integrasi cloud computing, serta implementasi AI/ML untuk otomasi layanan.",
            Tone.ORANGE,
        ),
        Pillar(
            "Product Strategy & Metrics",
            "Perumusan lean canvas, analisa product-market fit, pengujian metrik adopsi pengguna, dan manajemen sprint proyek yang tangguh.",
            Tone.BLUE,
        ),
        Pillar(
            "EdTech & LIDM Puspresnas",
            "Spesialisasi riset pedagogi digital, microlearning interactive media, dan penguasaan 5 divisi cabang lomba nasional LIDM.",
            Tone.ORANGE,
        ),
    )

    val history = listOf(
        HistoryItem(
            "2022", "Pondasi Awal & Medali Pertama",
            "Gagasan awal KMID diprakarsai oleh gabungan alumni tim delegasi UGM yang sukses merebut medali dalam LIDM Puspresnas. Diinisiasi sharing session santai mengenai kebutuhan paguyuban inkubasi berkelanjutan.",
            "Tonggak: 12 Anggota Pelopor", Tone.BLUE,
        ),
        HistoryItem(
            "2023", "Formalisasi Binaan Subdit Ditmawa",
            "KMID resmi disahkan sebagai komunitas minat bakat & penalaran di bawah Subdirektorat Kreativitas Mahasiswa UGM. Penyusunan AD/ART pertama dan sistem mentoring terstruktur antar fakultas.",
            "Tonggak: SK Resmi Pembinaan", Tone.ORANGE,
        ),
        HistoryItem(
            "2025", "Ekspansi Divisi & Bootcamp Kilat",
            "Peluncuran program Digital Talent Bootcamp lintas prodi. Menghubungkan riset dosen pembimbing dengan prototipe buatan mahasiswa, memperluas cakupan divisi dari UI/UX hingga Software Cloud.",
            "Tonggak: 80+ Delegasi Terlatih", Tone.BLUE,
        ),
        HistoryItem(
            "2026", "Juara Umum & Hub Hilirisasi",
            "KMID berhasil mengantar kontingen UGM menyabet predikat Juara Umum LIDM Puspresnas. Melangkah lebih jauh ke arah akselerasi prototipe digital menjadi produk tepat guna bagi masyarakat.",
            "Tonggak: Rekor Juara Bertahan", Tone.BLUE, highlighted = true,
        ),
    )

    val aboutGallery = listOf(
        GalleryPhoto("Gelar Juara Divisi LIDM"),
        GalleryPhoto("Riset Kurikulum & User Research Sprint"),
        GalleryPhoto("Grand Launching & Onboarding Kepengurusan"),
    )

    val missions = listOf(
        Mission(
            "Membina Kompetensi Teknis & Design Thinking",
            "Menyelenggarakan kurikulum workshop, peer-learning, dan review portofolio secara intensif agar anggota menguasai standar teknologi industri terkini.",
            Tone.BLUE,
        ),
        Mission(
            "Mengakselerasi Delegasi Kompetisi Nasional",
            "Menyiapkan tim kontingen unggulan UGM melalui simulasi berkala, bimbingan dosen pakar, dan telaah rubrik penjurian resmi Puspresnas Kemendikbudristek.",
            Tone.ORANGE,
        ),
        Mission(
            "Membangun Kolaborasi Multidisipliner Nyata",
            "Mengikis sekat silo fakultas dengan memasangkan mahasiswa informatika, desain, ilmu pendidikan, hingga humaniora dalam satu kesatuan produk sinergis.",
            Tone.ORANGE,
        ),
        Mission(
            "Mendorong Hilirisasi Solusi Digital Berdampak",
            "Memastikan produk purwarupa tidak mandek di atas kertas kompetisi, namun diuji coba dan didistribusikan kepada sekolah, UMKM, dan masyarakat mitra.",
            Tone.BLUE,
        ),
    )

    // Nama pengurus di bawah masih dummy dari desain.
    val advisors = listOf(
        Advisor("Dosen Pembina Utama", Tone.BLUE, "Dr. Ir. Tariq Hifzhillah, M.Eng.", "Departemen Teknik Elektro & Informatika, Sekolah Vokasi"),
        Advisor("Penasihat Kemahasiswaan", Tone.ORANGE, "Tariq Hifzhillah, S.Kom., M.Cs.", "Subdirektorat Kreativitas Mahasiswa, Ditmawa UGM"),
    )

    val bph = List(4) {
        Member("Ketua Umum", "Ahmad Tariq Hifzhillah", "Teknologi Informasi 2025 • Fakultas Teknik", "ahmad@mail.ugm.ac.id")
    }

    val divisionHeads = List(4) {
        Member("Kadiv Media Creative", "Ahmad Tariq Hifzhillah", "Teknologi Rekayasa Perangkat Lunak 2025 • Sekolah Vokasi")
    }

    // ---------- ACTIVITIES ----------
    const val PROGRAMS_INTRO =
        "Program terstruktur berdurasi mingguan hingga semesteran yang dirancang untuk mentransformasi minat digital menjadi portofolio inovasi berdampak nyata."

    private val programRow = listOf(
        Program("Seminar", LOREM, "KOMPETISI PUSPRESNAS", Tone.BLUE, "Maret – Juni 2025", "Zoom/Google meet", Tone.GREEN),
        Program("Networking & Softskill", LOREM, "CAREER & SOFTSKILL", Tone.ORANGE, "Sabtu & Minggu", "Zoom/Google Meet", Tone.BLUE),
        Program("Digital Bootcamp & Mentoring", LOREM, "HACKATHON", Tone.PURPLE, "12–14 April 2025", "GIK UGM", Tone.PURPLE),
    )
    val programs = programRow + programRow

    val agendaMonths = listOf("Mar", "Apr", "Mei", "Jun")

    // Desain hanya menampilkan agenda bulan Mei. Agenda bulan lain = CONTOH.
    val agendas = listOf(
        Agenda("Mar", 15, 3, 2025, "Kickoff Kurikulum KMID", "Pembukaan", "Pembukaan program semester dan pembagian kelompok mentoring.", "GIK UGM"),
        Agenda("Apr", 12, 4, 2025, "Digital Bootcamp & Mentoring", "Hackathon", "Sprint tiga hari membangun prototipe bersama mentor.", "GIK UGM"),
        Agenda("Mei", 18, 5, 2025, "Simulasi Pitching Delegasi Nasional", "Mentoring Juri", "Gladi bersih presentasi dan uji tanya jawab bersama juri nasional independen.", "DILo Yogyakarta / Hybrid"),
        Agenda("Mei", 25, 5, 2025, "Simulasi Pitching Delegasi Nasional", "Mentoring Juri", "Gladi bersih presentasi dan uji tanya jawab bersama juri nasional independen.", "DILo Yogyakarta / Hybrid"),
        Agenda("Jun", 14, 6, 2025, "Innovation Showcase", "Showcase", "Pameran karya akhir anggota di depan mitra dan dosen.", "GIK UGM", startHour = 13, durationHours = 3),
    )

    const val REMINDER_TITLE = "Jangan Lewatkan Momentum Inovasi Digital di UGM"
    const val REMINDER_TEXT =
        "Seluruh linimasa kegiatan telah terintegrasi dengan kalender akademik universitas dan Puspresnas. " +
            "Mahasiswa terdaftar dapat mengaktifkan notifikasi pengingat otomatis agar tidak terlewat sesi bootcamp, " +
            "pembinaan, maupun tenggat submission."
    val reminderPoints = listOf(
        "Pengingat otomatis H-3 deadline pengumpulan berkas",
        "Tautan langsung Google Meet untuk sesi mentoring virtual",
        "Akses materi presentasi & rekaman kurikulum pasca acara",
    )

    const val GALLERY_INTRO =
        "Kilasan momen dedikasi mahasiswa KMID UGM dalam riset produk digital, ajang perlombaan tingkat nasional, hingga penganugerahan kejuaraan."

    val activities = listOf(
        Activity(
            "LAB INOVASI", Tone.ORANGE, "Laboratorium Terpadu Gedung Tahir UGM",
            "Hands-on UI/UX Prototyping & Modern Frontend Clinic",
            "Sesi mentoring intensif pembedahan desain sistem atomik dan pengkabelan API bersama mahasiswa Sekolah Vokasi dan Fasilkom.",
        ),
        Activity(
            "PODIUM PRESTASI", Tone.BLUE, "Puspresnas Kemdikbudristek",
            "Penganugerahan Juara Umum Delegasi KMID UGM",
            "Kemenangan membanggakan kontingen inovasi digital UGM membawa pulang medali emas dan penghargaan poster terbaik.",
        ),
        Activity("DESIGN SPRINT", Tone.DARK, null, "Affinity Mapping Ide Solusi", "Sesi 48 jam validasi pain point civitas kampus."),
        Activity("DEMO DAY", Tone.DARK, null, "Pitch Deck Live Presentation", "Demo prototipe aplikasi di depan kurator industri."),
        Activity("COMMUNITY", Tone.DARK, null, "Temu Akrab & Welcoming Party", "Membangun koneksi lintas angkatan di Gelanggang UGM."),
    )

    // ---------- IMPACT ----------
    const val IMPACT_TAGLINE =
        "Menghadirkan solusi teknologi berdampak dan memborong prestasi nasional untuk almamater dan Indonesia."

    val topStats = listOf(
        Stat("8", "Medali Nasional", Tone.BLUE),
        Stat("80%", "Divisi Lolos Final", Tone.ORANGE),
    )

    const val INCUBATION_TEXT =
        "Wadah terintegrasi UGM untuk mencetak talenta unggul dan solusi digital inovatif melalui 5 divisi utama:"

    val lidmDivisions = listOf(
        "Inovasi Teknologi Pendidikan",
        "Media Pembelajaran Digital",
        "Desain Pengalaman Pengguna (UI/UX)",
        "Video Edukasi Kreatif",
        "Digital Microteaching",
    )

    // Desain mengulang kartu yang sama. Ganti dengan karya asli.
    val lidmWorks = List(9) {
        LidmWork("EMAS • LIDM 2024", "EduQuest Interactive", "Platform gamifikasi pembelajaran adaptif untuk daerah 3T di Indonesia.")
    }

    val partners = listOf(
        Partner("GIK.ugm", "Media Partner", Tone.GREEN),
        Partner("ASSETS", "Learning Partner", Tone.BLUE),
        Partner("UGM", "Media Partner", Tone.RED),
        Partner("KOMATIK UGM", "Learning Partner", Tone.DARK),
        Partner("GDGOC UGM", "Student Developer", Tone.BLUE),
        Partner("KOMATIK UGM", "Learning Partner", Tone.DARK),
    )

    // ---------- INFORMATION ----------
    const val INFORMATION_SUBTITLE =
        "Menghadirkan solusi teknologi berdampak dan memborong prestasi nasional untuk almamater dan Indonesia."

    val oprecInfo = OprecInfo(
        deadline = "28 Feb 2025",
        title = "Open Recruitment Pengurus KMID Periode 2025/2026",
        description = "Bergabunglah bersama keluarga inovasi digital UGM untuk mengelola kompetisi, workshop teknologi, dan jejaring industri.",
        departments = listOf("Media Creative", "Humas & Publikasi", "IPDP", "ITDP"),
        target = "UGM Active Students",
    )

    val pengumumanPoints = listOf(
        "Transparansi Seleksi Kontingen",
        "Bimbingan Mentor Eksklusif",
        "Akses Fasilitas Lab & Cloud",
    )

    val nationalCompetitions = listOf(
        InfoKompetisi(
            tag = "LIDM 2026",
            title = "LIDM Puspresnas 2025",
            description = "Divisi Inovasi Pembelajaran Digital & Microlearning. Batas pengumpulan proposal internal UGM 15 Maret.",
        ),
        InfoKompetisi(
            tag = "LIDM 2026",
            title = "LIDM Puspresnas 2025",
            description = "Divisi Inovasi Pembelajaran Digital & Microlearning. Batas pengumpulan proposal internal UGM 15 Maret.",
        ),
    )

    // ---------- JOIN ----------
    val joinStats = listOf(
        Stat("9", "Divisi Peminatan", Tone.BLUE),
        Stat("100%", "Mentoring", Tone.ORANGE),
        Stat("UGM", "Mahasiswa D4 & S1", Tone.BLUE),
    )

    val timelineSteps = listOf(
        SelectionStep(
            stepNumber = "01",
            dateRange = "1 – 15 MARET 2025",
            title = "Pendaftaran & Berkas",
            description = "Pengisian formulir identitas diri, pengunggahan CV kreatif, serta dokumen portofolio karya awal melalui tautan resmi.",
            tag = "Tahap Awal Wajib",
            isOrange = true,
            dateColor = Tone.ORANGE,
        ),
        SelectionStep(
            stepNumber = "02",
            dateRange = "18 – 24 MARET 2025",
            title = "Sesi Wawancara",
            description = "Diskusi mendalam bersama BPH seputar motivasi, komitmen waktu, serta peninjauan teknis hasil pengerjaan case study.",
            tag = "Wawancara Daring / Luring",
            isOrange = false,
            dateColor = Tone.BLUE,
        ),
        SelectionStep(
            stepNumber = "03",
            dateRange = "5 APRIL 2025",
            title = "Pengumuman Kelulusan",
            description = "Pengumuman resmi staf baru KMID UGM 2025 dilanjutkan dengan First Gathering & Onboarding ekosistem kerja inovatif.",
            tag = "Pengumuman Kelulusan",
            isOrange = false,
            dateColor = Tone.GREEN,
        ),
    )

    val divisionRequirements = listOf(
        DivisionRequirement(
            divisionName = "Media Creative",
            description = "Menciptakan pengalaman desain yang intuitif, estetis, dan informatif.",
            requirements = listOf(
                "Mahasiswa aktif Universitas Gadjah Mada jenjang D4 maupun S1 minimal semester 2 hingga semester 6.",
                "Menguasai tools desain terutama Figma.",
                "Memahami metodologi Design Thinking, serta perancangan Informasi pada desain.",
                "Mampu bekerja sama antar tim maupun antar divisi.",
                "Wajib melampirkan portofolio (Behance, Dribbble, Google Drive, atau Figma file link).",
            ),
        ),
        DivisionRequirement(
            divisionName = "Humas & Publikasi",
            description = "Mengelola komunikasi publik, media sosial, dan hubungan antar lembaga UGM.",
            requirements = listOf(
                "Mahasiswa aktif Universitas Gadjah Mada jenjang D4 maupun S1 minimal semester 2 hingga semester 6.",
                "Memiliki kemampuan copywriting dan komunikasi publik yang baik.",
                "Kreatif dalam merancang konten media sosial (Instagram, LinkedIn, TikTok).",
                "Mampu berkoordinasi dengan pihak internal dan eksternal kampus.",
            ),
        ),
        DivisionRequirement(
            divisionName = "Perlombaan",
            description = "Mengoordinasikan kontingen dan delegasi kompetisi nasional Puspresnas.",
            requirements = listOf(
                "Mahasiswa aktif UGM D4/S1 semester 2 hingga 6.",
                "Memahami alur dan regulasi kompetisi nasional seperti LIDM dan Gemastik.",
                "Memiliki kemampuan manajemen proyek dan koordinasi tim yang kuat.",
            ),
        ),
        DivisionRequirement(
            divisionName = "IPDP",
            description = "Divisi Inovasi Pembelajaran Digital (IPDP) Puspresnas.",
            requirements = listOf(
                "Mahasiswa aktif UGM D4/S1 semester 2 hingga 6.",
                "Tertarik pada riset media pembelajaran berbasis teknologi.",
                "Mampu menyusun proposal inovasi dan prototipe produk edukasi digital.",
            ),
        ),
        DivisionRequirement(
            divisionName = "ITDP",
            description = "Divisi Inovasi Teknologi Digital & Programming.",
            requirements = listOf(
                "Mahasiswa aktif UGM D4/S1 semester 2 hingga 6.",
                "Menguasai dasar pemrograman web, mobile, atau cloud architecture.",
                "Terbiasa menggunakan Git dan siap berkolaborasi dalam pengkodean tim.",
            ),
        ),
        DivisionRequirement(
            divisionName = "Poster Digital",
            description = "Perancangan poster edukasi interaktif dan media komunikasi visual.",
            requirements = listOf(
                "Mahasiswa aktif UGM D4/S1 semester 2 hingga 6.",
                "Kreatif dalam ilustrasi visual dan tata letak poster ilmiah/edukasi.",
                "Memahami prinsip keterbacaan dan estetika komunikasi gambar.",
            ),
        ),
        DivisionRequirement(
            divisionName = "MicroTeaching Digital",
            description = "Pengembangan media microteaching interaktif berbasis pedagogi modern.",
            requirements = listOf(
                "Mahasiswa aktif UGM D4/S1 semester 2 hingga 6.",
                "Tertarik pada metode pengajaran digital dan pembuatan video pembelajaran.",
                "Memiliki keahlian public speaking dan penyusunan modul materi.",
            ),
        ),
    )
}

