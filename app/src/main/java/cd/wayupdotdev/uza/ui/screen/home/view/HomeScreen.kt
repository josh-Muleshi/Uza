package cd.wayupdotdev.uza.ui.screen.home.view

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cd.wayupdotdev.uza.AuthActivity
import cd.wayupdotdev.uza.destinations.SettingScreenDestination
import cd.wayupdotdev.uza.ui.screen.home.business.HomeViewModel
import cd.wayupdotdev.uza.ui.screen.home.component.BarScreenItem
import cd.wayupdotdev.uza.ui.screen.home.component.ChipTab
import cd.wayupdotdev.uza.ui.screen.home.component.SearchBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun HomeScreen(navigator: DestinationsNavigator, viewModel: HomeViewModel = hiltViewModel()){

    val context = LocalContext.current
    val posts by viewModel.data.collectAsState()

    BackHandler {
        (context as? Activity)?.finish()
    }

    val tabs = listOf("Tous", "vetements", "Montre", "Ordi", "Telephone")

    var selectedTabIndex by remember { mutableStateOf(tabs[0]) }

    val gridItemsr = listOf("Grid 1", "Grid 2", "Grid 3", "Grid 4", "")
    val cols = 2

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            BarScreenItem(
                onProfileBtnClicked = {
                    val intent = Intent(context, AuthActivity::class.java)
                    context.startActivity(intent)
                },
                onNotificationBtnClicked = {},
                onSettingsBtnClicked = { navigator.navigate(SettingScreenDestination) }
            )
        }

        item {
            SearchBar(onSearch = {})
        }

        item {
            ChipTab(
                itemsList = tabs,
                selectedItem = selectedTabIndex,
            ) { tabIndex ->
                selectedTabIndex = tabIndex
            }
        }

        items(gridItemsr.chunked(cols)) { items ->
            Row {
                for ((index, item) in items.withIndex()) {

                    Column(modifier = Modifier.fillMaxWidth(1f / (cols - index)).padding(12.dp)) {
                        Item(index = index)
                        Text(
                            text = item,
                        )
                    }
                }
            }
        }

        item {
            Text(
                text = "<--   post end   -->",
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                color = Color.LightGray,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.padding(22.dp))
        }

    }
}


@Composable
fun Item(index : Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 200.dp)
            .background(color = Color.Magenta, shape = RoundedCornerShape(size = 4.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$index",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
