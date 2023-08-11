package cd.wayupdotdev.uza.navigation

import cd.wayupdotdev.uza.destinations.AboutScreenDestination
import cd.wayupdotdev.uza.destinations.AuthScreenDestination
import cd.wayupdotdev.uza.destinations.BrowseScreenDestination
import cd.wayupdotdev.uza.destinations.DetailScreenDestination
import cd.wayupdotdev.uza.destinations.HomeScreenDestination
import cd.wayupdotdev.uza.destinations.MainScreenDestination
import cd.wayupdotdev.uza.destinations.ProfileScreenDestination
import cd.wayupdotdev.uza.destinations.SettingScreenDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

private val destinationsList = listOf(
    ProfileScreenDestination,
    AuthScreenDestination,
    DetailScreenDestination,
    SettingScreenDestination,
    HomeScreenDestination,
    BrowseScreenDestination,
    AboutScreenDestination,
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