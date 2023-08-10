package cd.wayupdotdev.uza.ui.screen.add.view

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cd.wayupdotdev.mytown.presentation.screen.post.business.AddViewModel
import cd.wayupdotdev.uza.destinations.MainScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.glide.GlideImage

@Destination
@Composable
fun PostMakeScreen(navigator: DestinationsNavigator, uri: Uri, viewModel: AddViewModel = hiltViewModel()) {

    var comment by remember { mutableStateOf("") }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screeHeight = configuration.screenHeightDp.dp

    LazyColumn {

        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(120.dp)
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navigator.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "close",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Button(
                        enabled = comment.isNotEmpty(),
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),
                        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
                        onClick = {
                            viewModel.addPost(
                                comment,
                                uri
                            )
                            navigator.navigate(MainScreenDestination)
                        }
                    ) {
                        Text(text = "Enregistrer")
                    }
                }
            }
        }

        item {
            Column(modifier = Modifier.fillMaxSize()) {
                TextField(
                    value = comment,
                    onValueChange = { comment = it },
                    placeholder = { Text("Ajoutez un commentaire...") },
                    textStyle = TextStyle(
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        fontWeight = FontWeight.Medium
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 0.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background,
                        focusedIndicatorColor = MaterialTheme.colors.background,
                        unfocusedIndicatorColor = MaterialTheme.colors.background
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, end = 24.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    GlideImage(
                        imageModel = uri,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(screenWidth * 0.75f)
                            .height(screeHeight * 0.45f)
                            .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp)))

                    )
                }
            }
        }
    }
}