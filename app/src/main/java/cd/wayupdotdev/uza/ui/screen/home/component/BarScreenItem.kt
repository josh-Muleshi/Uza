package cd.wayupdotdev.uza.ui.screen.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cd.wayupdotdev.uza.R
import cd.wayupdotdev.uza.ui.theme.ItemGray

@Composable
fun BarScreenItem (onProfileBtnClicked: () -> Unit, onNotificationBtnClicked: () -> Unit, onSettingsBtnClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 5.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Image(
            painter = painterResource(id = R.drawable.logo_uza_three),
            contentDescription = "profile",
            modifier = Modifier
                .size(45.dp)
                .clip(shape = CircleShape)
                .border(2.dp, color = ItemGray,shape = CircleShape)
                .clickable {
                    onProfileBtnClicked()
                }
        )

        Row(modifier = Modifier.padding(12.dp)) {
            IconButton(onClick = onNotificationBtnClicked) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    tint = Color.Gray,
                    contentDescription = "notification",
                    modifier = Modifier
                        .size(25.dp)
                )
            }

            IconButton(onClick = onSettingsBtnClicked) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    tint = Color.Gray,
                    contentDescription = "settings",
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        }
    }
}