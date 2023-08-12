package cd.wayupdotdev.uza.ui.screen.home.view

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cd.wayupdotdev.uza.AuthActivity
import cd.wayupdotdev.uza.data.model.Post
import cd.wayupdotdev.uza.data.model.User
import cd.wayupdotdev.uza.destinations.DetailScreenDestination
import cd.wayupdotdev.uza.destinations.NotificationScreenDestination
import cd.wayupdotdev.uza.destinations.SettingScreenDestination
import cd.wayupdotdev.uza.ui.screen.home.business.HomeState
import cd.wayupdotdev.uza.ui.screen.home.business.HomeViewModel
import cd.wayupdotdev.uza.ui.screen.home.component.BarScreenItem
import cd.wayupdotdev.uza.ui.screen.home.component.ChipTab
import cd.wayupdotdev.uza.ui.screen.home.component.SearchBar
import cd.wayupdotdev.uza.ui.screen.profile.business.ProfileState
import cd.wayupdotdev.uza.ui.theme.ItemGray
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.glide.GlideImage

@Destination
@Composable
fun HomeScreen(navigator: DestinationsNavigator, viewModel: HomeViewModel = hiltViewModel()){

    val context = LocalContext.current
    val posts by viewModel.data.collectAsState()
    val state by viewModel.state.collectAsState()

    BackHandler {
        (context as? Activity)?.finish()
    }

    val tabs = listOf("Tous", "vetements", "Montre", "Ordi", "Telephone")

    var selectedTabIndex by remember { mutableStateOf(tabs[0]) }

    val cols = 2

    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {
        item {
            if (state is ProfileState.Success) {
                val user = (state as ProfileState.Success).user
                BarScreenItem(
                    user = user,
                    onProfileBtnClicked = {
                        val intent = Intent(context, AuthActivity::class.java)
                        context.startActivity(intent)
                    },
                    onNotificationBtnClicked = { navigator.navigate(NotificationScreenDestination) },
                    onSettingsBtnClicked = { navigator.navigate(SettingScreenDestination) }
                )
            } else {
                BarScreenItem(
                    user = User(),
                    onProfileBtnClicked = {
                        val intent = Intent(context, AuthActivity::class.java)
                        context.startActivity(intent)
                    },
                    onNotificationBtnClicked = { navigator.navigate(NotificationScreenDestination) },
                    onSettingsBtnClicked = { navigator.navigate(SettingScreenDestination) }
                )
            }
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

        if (posts is HomeState.Success) {
            items((posts as HomeState.Success).posts.chunked(cols)) { items ->
                Row {
                    for ((index, item) in items.withIndex()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(1f / (cols - index))
                                .padding(4.dp)
                        ) {
                            Item(
                                post = item,
                                selectedItem = { post ->
                                    navigator.navigate(DetailScreenDestination(postUid = post.uid))
                                }
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.padding(22.dp))
        }
    }
}


@Composable
fun Item(
    post: Post,
    selectedItem: (Post) -> (Unit)
) {
    Column(
        modifier = Modifier
        .clickable {
            selectedItem(post)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 190.dp)
                .background(
                    color = ItemGray,
                    shape = RoundedCornerShape(size = 16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            GlideImage(
                imageModel = post.imageUrl,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(size = 16.dp))
            )
        }

        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = post.title,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                maxLines = 2
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Text(
                text = post.description,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                maxLines = 1
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Text(
                text = post.devise + post.price.toString(),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                maxLines = 1
            )
        }
    }
}
