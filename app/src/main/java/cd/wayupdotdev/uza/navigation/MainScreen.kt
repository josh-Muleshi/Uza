package cd.wayupdotdev.uza.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cd.wayupdotdev.uza.destinations.HomeScreenDestination
import cd.wayupdotdev.uza.ui.theme.ItemGray
import cd.wayupdotdev.uza.ui.theme.Purple80
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate


@Destination
@Composable
fun MainScreen() {

    val context = LocalContext.current

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val startRoute = HomeScreenDestination

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
          FloatingActionButton(
              modifier = Modifier
                  .clip(shape = CircleShape)
                  .background(Color.Black)
                  .padding(10.dp),
              shape = CircleShape,
              onClick = {
//                  val intent = Intent(context, AddPostActivity::class.java)
//                  context.startActivity(intent)
                  navController.navigate(getBottomNavItems()[1].destination)
              },
              backgroundColor = Purple80
          ) {
              Icon(imageVector = Icons.Default.Add, tint = Color.Black, contentDescription = "add")
          }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        drawerElevation = 0.dp,
        bottomBar = {
            BottomAppBar(backgroundColor = Color.Black, elevation = 0.dp) {
                BottomNavigation(backgroundColor = Color.Black, elevation = 0.dp) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    getBottomNavItems().forEach { screen ->
                        val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                        BottomNavigationItem(
                            selected = isSelected,
                            onClick = { navController.navigate(screen.destination) },
                            selectedContentColor = Color.White,
                            unselectedContentColor = ItemGray,
                            label = {
                                Text(text = screen.label, fontSize = 10.sp)
                            },
                            icon = {
                                if (screen.label != "Ajouter"){
                                    Column {
                                        if (isSelected) {
                                            Icon(
                                                imageVector = Icons.Default.Minimize,
                                                tint = Purple80,
                                                contentDescription = null
                                            )
                                        }
                                        Icon(
                                            imageVector = screen.icon as ImageVector,
                                            tint = if (isSelected) Color.White else ItemGray,
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
            }
        },
        content = { contentPadding ->
            DestinationsNavHost(
                navGraph = HomeNavGraph,
                startRoute = startRoute,
                navController = navController,
                modifier = Modifier.padding(contentPadding)
            )
        }
    )
}