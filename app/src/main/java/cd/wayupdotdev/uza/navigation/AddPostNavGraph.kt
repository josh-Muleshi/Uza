package cd.wayupdotdev.uza.navigation

import cd.wayupdotdev.uza.destinations.AddPostScreenDestination
import cd.wayupdotdev.uza.destinations.AddScreenDestination
import cd.wayupdotdev.uza.destinations.MainScreenDestination
import cd.wayupdotdev.uza.destinations.PostMakeScreenDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

private val destinationsList = listOf(
    MainScreenDestination,
    AddScreenDestination,
    AddPostScreenDestination,
    PostMakeScreenDestination
)

object AddPostNavGraph : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>>
        get() = destinationsList.associateBy { it.route }

    override val route: String
        get() = "Second_route"

    override val startRoute: Route
        get() = AddScreenDestination
}