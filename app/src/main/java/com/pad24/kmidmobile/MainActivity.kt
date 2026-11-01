package com.pad24.kmidmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pad24.kmidmobile.ui.components.KmidTopBar
import com.pad24.kmidmobile.ui.navigation.KmidDrawer
import com.pad24.kmidmobile.ui.navigation.Screen
import com.pad24.kmidmobile.ui.screens.AboutScreen
import com.pad24.kmidmobile.ui.screens.ActivitiesScreen
import com.pad24.kmidmobile.ui.screens.BerandaScreen
import com.pad24.kmidmobile.ui.screens.ImpactScreen
import com.pad24.kmidmobile.ui.screens.InformationScreen
import com.pad24.kmidmobile.ui.screens.JoinScreen
import com.pad24.kmidmobile.ui.theme.KmidTheme
import kotlinx.coroutines.launch

/**
 * MainActivity: menu hamburger berisi Beranda, About, Activities, Impact, Information, Join KMID.
 * Tiap menu adalah layar Compose di dalam satu NavHost.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { KmidTheme { KmidApp() } }
    }
}

@androidx.compose.runtime.Composable
fun KmidApp() {
    val nav = rememberNavController()
    val drawer = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val backStack by nav.currentBackStackEntryAsState()
    val current = backStack?.destination?.route

    fun go(screen: Screen) {
        scope.launch { drawer.close() }
        if (current == screen.route) return
        nav.navigate(screen.route) {
            popUpTo(Screen.Beranda.route) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    val onJoin: () -> Unit = {
        scope.launch { drawer.close() }
        go(Screen.Join)
    }

    ModalNavigationDrawer(
        drawerState = drawer,
        drawerContent = {
            KmidDrawer(
                current = current,
                onNavigate = ::go,
                onClose = { scope.launch { drawer.close() } },
                onJoinClick = onJoin,
            )
        },
    ) {
        Scaffold(
            containerColor = Color.White,
            topBar = { KmidTopBar(onMenuClick = { scope.launch { drawer.open() } }) },
        ) { padding ->
            NavHost(nav, startDestination = Screen.Beranda.route, modifier = Modifier.padding(padding)) {
                composable(Screen.Beranda.route) {
                    BerandaScreen(onJoinClick = onJoin, onNavigate = ::go)
                }
                composable(Screen.About.route) { AboutScreen(onNavigate = ::go) }
                composable(Screen.Activities.route) { ActivitiesScreen(onNavigate = ::go) }
                composable(Screen.Impact.route) { ImpactScreen(onNavigate = ::go) }
                composable(Screen.Information.route) { InformationScreen(onNavigate = ::go, onJoinClick = onJoin) }
                composable(Screen.Join.route) { JoinScreen(onNavigate = ::go) }
            }
        }
    }
}
