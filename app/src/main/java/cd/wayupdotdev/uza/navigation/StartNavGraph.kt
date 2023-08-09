package cd.wayupdotdev.uza.navigation

import cd.wayupdotdev.uza.destinations.MainScreenDestination
import cd.wayupdotdev.uza.destinations.OnBoardingScreenDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

private val destinationsList = listOf(
    OnBoardingScreenDestination,
    MainScreenDestination
)

object StartNavGraph : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>>
        get() = destinationsList.associateBy { it.route }

    override val route: String
        get() = "root"

    override val startRoute: Route
        get() = MainScreenDestination
}