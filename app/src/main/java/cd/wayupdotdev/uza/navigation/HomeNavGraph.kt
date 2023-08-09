package cd.wayupdotdev.uza.navigation

import cd.wayupdotdev.uza.destinations.AddScreenDestination
import cd.wayupdotdev.uza.destinations.BrowseScreenDestination
import cd.wayupdotdev.uza.destinations.HomeScreenDestination
import cd.wayupdotdev.uza.destinations.MainScreenDestination
import cd.wayupdotdev.uza.destinations.ProfileScreenDestination
import cd.wayupdotdev.uza.destinations.SettingsScreenDestination
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