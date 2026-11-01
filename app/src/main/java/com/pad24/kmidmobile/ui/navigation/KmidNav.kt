package com.pad24.kmidmobile.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pad24.kmidmobile.ui.components.KmidButton
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.pad24.kmidmobile.R
import com.pad24.kmidmobile.ui.theme.KmidColors

/** Rute layar di dalam MainActivity (menu hamburger). */
enum class Screen(val route: String, val label: String) {
    Beranda("beranda", "Home"),
    About("about", "About"),
    Activities("activities", "Activities"),
    Impact("impact", "Impact"),
    Information("information", "Information"),
    Join("join", "Join KMID"),
}

/** Isi menu hamburger: logo + tutup, kolom cari, daftar menu, tombol Join KMID oranye. */
@Composable
fun KmidDrawer(
    current: String?,
    onNavigate: (Screen) -> Unit,
    onClose: () -> Unit,
    onJoinClick: () -> Unit,
) {
    var query by remember { mutableStateOf("") }
    ModalDrawerSheet(drawerContainerColor = Color.White) {
        Column(Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 12.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Image(painterResource(R.drawable.logo_kmid), contentDescription = "KMID", modifier = Modifier.height(32.dp))
                Spacer(Modifier.weight(1f))
                IconButton(onClick = onClose) { Icon(Icons.Filled.Close, contentDescription = "Tutup menu") }
            }
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Cari") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
            )
            Spacer(Modifier.height(12.dp))

            val items = Screen.entries.filter { (it != Screen.Join) && it.label.contains(query, ignoreCase = true) }
            items.forEach { screen ->
                NavigationDrawerItem(
                    label = { Text(screen.label, style = MaterialTheme.typography.titleSmall) },
                    selected = current == screen.route,
                    onClick = { onNavigate(screen) },
                    badge = { Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null) },
                    shape = RoundedCornerShape(10.dp),
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = KmidColors.BlueSoft,
                        selectedTextColor = KmidColors.Blue,
                    ),
                )
                HorizontalDivider(color = KmidColors.Line)
            }

            Spacer(Modifier.weight(1f))
            KmidButton(
                text = "Join KMID",
                onClick = onJoinClick,
                modifier = Modifier.fillMaxWidth(),
                container = KmidColors.Orange,
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}
