package cd.wayupdotdev.uza.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ScreenSearchDesktop
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ScreenSearchDesktop
import androidx.compose.ui.graphics.vector.ImageVector
import cd.wayupdotdev.uza.destinations.BrowseScreenDestination
import cd.wayupdotdev.uza.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.spec.Direction

sealed class Screen(val destination: Direction, val route: String = destination.route, val label: String = "", val icon: ImageVector? = null, val iconSelected: ImageVector? = null){
    object Home : Screen(destination = HomeScreenDestination, label = "Accueil", icon = Icons.Outlined.Home, iconSelected = Icons.Default.Home)
    object Add : Screen(destination = HomeScreenDestination, label = "Ajouter" , icon = Icons.Outlined.Add, iconSelected = Icons.Default.Add)
    object Browse : Screen(destination = BrowseScreenDestination, label = "Parcourir",icon = Icons.Outlined.ScreenSearchDesktop, iconSelected = Icons.Default.ScreenSearchDesktop)
}

fun getBottomNavItems(): List<Screen> {
    return listOf(Screen.Home, Screen.Add, Screen.Browse)
}

