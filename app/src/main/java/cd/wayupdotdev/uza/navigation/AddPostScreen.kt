package cd.wayupdotdev.uza.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import cd.wayupdotdev.uza.destinations.AddScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AddPostScreen() {
    val navController = rememberNavController()

    val startRoute = AddScreenDestination
    DestinationsNavHost(
        navGraph = AddPostNavGraph,
        startRoute = startRoute,
        navController = navController
    )
}