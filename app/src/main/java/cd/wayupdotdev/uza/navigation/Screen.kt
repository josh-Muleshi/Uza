package cd.wayupdotdev.uza.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import cd.wayupdotdev.destinations.AddScreenDestination
import cd.wayupdotdev.destinations.BrowseScreenDestination
import cd.wayupdotdev.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.spec.Direction

sealed class Screen(val destination: Direction, val route: String = destination.route, val label: String = "", val icon: ImageVector? = null){
    object Home : Screen(destination = HomeScreenDestination, label = "Accueil", icon = Icons.Default.Home)
    object Add : Screen(destination = AddScreenDestination, label = "Ajouter" , icon = Icons.Default.Add)
    object Browse : Screen(destination = BrowseScreenDestination, label = "Recherche",icon = Icons.Default.Search)
}

fun getBottomNavItems(): List<Screen> {
    return listOf(Screen.Home, Screen.Add, Screen.Browse)
}

