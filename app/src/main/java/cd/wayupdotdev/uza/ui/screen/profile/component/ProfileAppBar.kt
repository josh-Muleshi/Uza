package cd.wayupdotdev.uza.ui.screen.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cd.wayupdotdev.uza.R
import cd.wayupdotdev.uza.ui.theme.BlackGray

@Composable
fun ProfileAppBar(
    backButton: () -> Unit,
    settingScreen: () -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    ){
        Row(
            modifier = Modifier
                .padding(start = 4.dp, end = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = backButton ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    tint = BlackGray,
                    contentDescription = "back"
                )
            }

            IconButton(onClick = settingScreen ) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    tint = BlackGray,
                    contentDescription = "settings",
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        }
    }
}