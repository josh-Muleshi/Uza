package cd.wayupdotdev.uza.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import cd.wayupdotdev.uza.destinations.AuthScreenDestination
import cd.wayupdotdev.uza.destinations.ProfileScreenDestination
import cd.wayupdotdev.uza.ui.viewModel.AuthRouteViewModel
import cd.wayupdotdev.uza.ui.viewModel.business.AuthRouteState
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.Route

@Destination
@Composable
fun AuthRootScreen(viewModel : AuthRouteViewModel = hiltViewModel()) {

    val isAuth by viewModel.isAuth.collectAsState()

    val navController = rememberNavController()

    var startRoute: Route = AuthScreenDestination

    when(isAuth){
        is AuthRouteState.Success -> {
            startRoute = if ((isAuth as AuthRouteState.Success).isAuth) {
                ProfileScreenDestination
            } else {
                AuthScreenDestination
            }
        }
        else -> {}
    }

    DestinationsNavHost(
        navGraph = AuthNavGraph,
        startRoute = startRoute,
        navController = navController
    )
}