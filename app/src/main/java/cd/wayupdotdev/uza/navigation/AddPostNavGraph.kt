package cd.wayupdotdev.uza.navigation

//import cd.wayupdotdev.mytown.destinations.AddPostScreenDestination
//import cd.wayupdotdev.mytown.destinations.MainScreenDestination
//import cd.wayupdotdev.mytown.destinations.PostMakeScreenDestination
//import cd.wayupdotdev.mytown.destinations.PostScreenDestination
//import com.ramcosta.composedestinations.spec.DestinationSpec
//import com.ramcosta.composedestinations.spec.NavGraphSpec
//import com.ramcosta.composedestinations.spec.Route
//
//private val destinationsList = listOf(
//    MainScreenDestination,
//    PostScreenDestination,
//    AddPostScreenDestination,
//    PostMakeScreenDestination
//)
//
//object AddPostNavGraph : NavGraphSpec {
//    override val destinationsByRoute: Map<String, DestinationSpec<*>>
//        get() = destinationsList.associateBy { it.route }
//
//    override val route: String
//        get() = "Second_route"
//
//    override val startRoute: Route
//        get() = PostScreenDestination
//}