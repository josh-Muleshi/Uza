package cd.wayupdotdev.uza.ui.screen.add.view

import android.content.Intent
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cd.wayupdotdev.uza.AuthActivity
import cd.wayupdotdev.uza.destinations.MainScreenDestination
import cd.wayupdotdev.uza.ui.screen.add.business.AddViewModel
import cd.wayupdotdev.uza.ui.theme.ItemGray
import cd.wayupdotdev.uza.ui.theme.Purple80
import cd.wayupdotdev.uza.ui.viewModel.business.AuthRouteState
import com.chargemap.compose.numberpicker.ListItemPicker
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.glide.GlideImage

@Destination
@Composable
fun PostMakeScreen(navigator: DestinationsNavigator, uri: Uri, viewModel: AddViewModel = hiltViewModel()) {

    val isAuth by viewModel.isAuth.collectAsState()
    val context = LocalContext.current

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screeHeight = configuration.screenHeightDp.dp

    var title by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    val possibleValues = listOf("$", "Fc")
    var devise by remember { mutableStateOf(possibleValues[0]) }

    val isNotEmptyField = title.isNotEmpty() and price.isNotEmpty() and quantity.isNotEmpty()


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
                        enabled = isNotEmptyField,
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),
                        colors = if (isNotEmptyField) {
                            ButtonDefaults.outlinedButtonColors(
                                backgroundColor = Color.Black
                            )
                        } else {
                            ButtonDefaults.outlinedButtonColors(
                                backgroundColor = ItemGray
                            )
                        },
                        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
                        onClick = {
                            when(isAuth){
                                is AuthRouteState.Success -> {
                                    if ((isAuth as AuthRouteState.Success).isAuth) {
                                        viewModel.addPost(
                                            title,
                                            destination,
                                            price.toDouble(),
                                            quantity.toInt(),
                                            devise,
                                            uri
                                        )
                                        navigator.navigate(MainScreenDestination)
                                    } else {
                                        val intent = Intent(context, AuthActivity::class.java)
                                        context.startActivity(intent)
                                    }
                                }
                                else -> {}
                            }

                        }
                    ) {
                        Text(text = "Enregistrer", color = if(isNotEmptyField) Purple80 else Color.Gray)
                    }
                }
            }
        }

        item {
            Column(modifier = Modifier.fillMaxSize()) {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text("Tapez le nom du produit") },
                    textStyle = TextStyle(
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        fontWeight = FontWeight.Bold
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

                TextField(
                    value = destination,
                    onValueChange = { destination = it },
                    placeholder = { Text("Ajoutez une description...") },
                    textStyle = TextStyle(
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        fontWeight = FontWeight.Normal
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

                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextField(
                        value = price,
                        onValueChange = { price = it },
                        placeholder = { Text("Le prix") },
                        textStyle = TextStyle(
                            fontSize = MaterialTheme.typography.subtitle1.fontSize,
                            fontWeight = FontWeight.Normal
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 0.dp)
                            .width(100.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.background,
                            focusedIndicatorColor = MaterialTheme.colors.background,
                            unfocusedIndicatorColor = MaterialTheme.colors.background
                        )
                    )

                    ListItemPicker(
                        label = { it },
                        value = devise,
                        onValueChange = { devise = it },
                        list = possibleValues,
                        dividersColor = Purple80
                    )
                }

                TextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    placeholder = { Text("La quantité") },
                    textStyle = TextStyle(
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        fontWeight = FontWeight.Normal
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 0.dp)
                        .width(150.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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