package cd.wayupdotdev.uza.navigation

import cd.wayupdotdev.uza.destinations.AddPostScreenDestination
import cd.wayupdotdev.uza.destinations.AuthScreenDestination
import cd.wayupdotdev.uza.destinations.MainScreenDestination
import cd.wayupdotdev.uza.destinations.ProfileScreenDestination
import cd.wayupdotdev.uza.destinations.SettingScreenDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

private val destinationsList = listOf(
    ProfileScreenDestination,
    AuthScreenDestination,
    MainScreenDestination,
    AddPostScreenDestination,
    SettingScreenDestination
)

object AuthNavGraph : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>>
        get() = destinationsList.associateBy { it.route }

    override val route: String
        get() = "auth_route"

    override val startRoute: Route
        get() = AuthScreenDestination
}