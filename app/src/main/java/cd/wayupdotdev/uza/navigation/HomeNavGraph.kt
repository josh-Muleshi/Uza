package cd.wayupdotdev.uza.navigation

import cd.wayupdotdev.destinations.AddScreenDestination
import cd.wayupdotdev.destinations.BrowseScreenDestination
import cd.wayupdotdev.destinations.HomeScreenDestination
import cd.wayupdotdev.destinations.MainScreenDestination
import cd.wayupdotdev.destinations.ProfileScreenDestination
import cd.wayupdotdev.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

private val destinationsList = listOf(
    ProfileScreenDestination,
    SettingsScreenDestination,
    AddScreenDestination,
    HomeScreenDestination,
    BrowseScreenDestination,
    MainScreenDestination
)

object HomeNavGraph : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>>
        get() = destinationsList.associateBy { it.route }

    override val route: String
        get() = "Main_route"

    override val startRoute: Route
        get() = HomeScreenDestination
}