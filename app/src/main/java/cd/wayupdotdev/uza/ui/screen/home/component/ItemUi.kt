package cd.wayupdotdev.uza.ui.screen.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cd.wayupdotdev.uza.R
import cd.wayupdotdev.uza.data.model.Post
import cd.wayupdotdev.uza.ui.theme.Black_camera
import cd.wayupdotdev.uza.ui.theme.Black_ic
import cd.wayupdotdev.uza.ui.theme.Purple80
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ItemUi(
    modifier: Modifier = Modifier,
    post: Post,
    onAddToFavorite: (Post) -> Unit,
    selectedItem: (Post) -> (Unit)
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 0.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        border = BorderStroke(0.5.dp, Purple80)
    ) {
        Box(
            modifier = modifier
                .height(220.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {

            GlideImage(
                imageModel = post.imageUrl,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        selectedItem(post)
                    }
            )

            BottomShadow(
                comment = post.description,
                onAddToFavorite = {}
            )
        }
    }
}

@Composable
fun BottomShadow(
    comment: String,
    onAddToFavorite: (/*Post*/) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0x22FFFFFF),
                        Black_ic,
                        Black_camera
                    )
                )
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.TopStart),
            onClick = { onAddToFavorite(/*post*/) }
        ) {
            Icon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = "favorite",
                tint = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .size(30.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier
                .padding(8.dp)
                .size(150.dp), contentAlignment = Alignment.BottomStart) {
                Text(
                    text = comment,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                )
            }

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
                    .border(
                        width = 1.dp,
                        color = Purple80,
                        RoundedCornerShape(corner = CornerSize(10.dp))
                    )
                    .background(color = Purple80)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Row {
                    Text(
                        text = "Commentaire",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.padding(2.dp))

                    Icon(painter = painterResource(id = R.drawable.ic_arrow_righ), contentDescription = null, tint = Color.White)
                }
            }
        }
    }
}