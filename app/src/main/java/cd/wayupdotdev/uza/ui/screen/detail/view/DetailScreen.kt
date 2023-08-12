package cd.wayupdotdev.uza.ui.screen.detail.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cd.wayupdotdev.uza.R
import cd.wayupdotdev.uza.data.model.Post
import cd.wayupdotdev.uza.ui.screen.detail.business.DetailState
import cd.wayupdotdev.uza.ui.screen.detail.business.DetailViewModel
import cd.wayupdotdev.uza.ui.theme.Black_ic
import cd.wayupdotdev.uza.ui.theme.Purple80
import cd.wayupdotdev.uza.ui.theme.WhiteTrans
import com.chargemap.compose.numberpicker.NumberPicker
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.glide.GlideImage

@Destination
@Composable
fun DetailScreen(navigator: DestinationsNavigator, postUid: String, viewModel: DetailViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val post by viewModel.data.collectAsState()

    LaunchedEffect(viewModel){
        viewModel.getPostByUid(postUid)
    }

    Scaffold { contentPadding ->
        if (post is DetailState.Success) {
            val customPost = (post as DetailState.Success).post
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(contentPadding)
            ) {
                DetailScreenContent(
                    customPost,
                    onAddToFavorite = {
                        viewModel.addToFavorite(it)
                        Toast.makeText(context, "Ajouté aux favoris", Toast.LENGTH_SHORT).show()
                    },
                    backButton = { navigator.navigateUp() }
                )
            }
        } else {
            //NoDataScreen()
        }
    }
}

@Composable
fun DetailScreenContent(post: Post, onAddToFavorite: (Post) -> Unit, backButton: () -> Unit) {

    var qt by remember { mutableStateOf(0) }

    LazyColumn(content = {
        item {
            BannerImageSection(post = post, onAddToFavorite = {onAddToFavorite.invoke(it)}, backButton = backButton)
        }

        item {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = post.title,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.padding(4.dp))

                Text(
                    text = post.description,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify,
                )

                Spacer(modifier = Modifier.padding(2.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = post.devise + post.price.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        maxLines = 1
                    )

                    NumberPicker(
                        value = qt,
                        range = 0..10,
                        onValueChange = {
                            qt = it
                        },
                        dividersColor = Purple80
                    )
                }
            }
        }

        item {
            OutlinedButton(
                modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(),
                onClick = {  },
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.Black,
                    contentColor = Purple80
                ),
                shape = RoundedCornerShape(50), // = 40% percent
            ) {
                androidx.compose.material3.Text(
                    text = "Passer la commande",
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                    color = Purple80,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    })
}

@Composable
fun BannerImageSection(modifier: Modifier = Modifier, post: Post, onAddToFavorite: (Post) -> Unit, backButton: () -> Unit) {
    Box(
        modifier = modifier
            .height(400.dp)
            .fillMaxWidth()
    ) {
        GlideImage(
            imageModel = post.imageUrl,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
        )

        DetailScreenTopSection(
            post = post,
            onAddToFavorite = { onAddToFavorite.invoke(it) },
            backButton = backButton
        )
    }
}

@Composable
fun DetailScreenTopSection(
    post: Post,
    onAddToFavorite: (Post) -> Unit,
    backButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Black_ic,
                        Color.Transparent
                    )
                )
            )
            .height(70.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .clickable { backButton() }
                    .size(35.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = WhiteTrans)
                    .padding(start = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "back", tint = Color.White)
            }

            Box(
                modifier = Modifier
                    .clickable { onAddToFavorite(post) }
                    .size(35.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = if (post.isFavorite) Color.Red else WhiteTrans)
                    .padding(start = 8.dp, end = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "favorite", tint = Color.White)
            }
        }
    }
}
