@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialNavigationApi::class
)

package cd.wayupdotdev.uza.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import cd.wayupdotdev.uza.destinations.OnBoardingScreenDestination
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@Destination
@RootNavGraph(start = true)
@Composable
fun SetupNavGraph() {

    val engine = rememberAnimatedNavHostEngine()
    val navController = rememberNavController()

    val startRoute = OnBoardingScreenDestination
    DestinationsNavHost(
        navGraph = StartNavGraph,
        startRoute = startRoute,
//        engine = engine,
        navController = navController
    )
}
