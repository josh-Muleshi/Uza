package cd.wayupdotdev.uza.ui.screen.onboarding


import androidx.annotation.FloatRange
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cd.wayupdotdev.uza.R
import cd.wayupdotdev.uza.destinations.MainScreenDestination
import cd.wayupdotdev.uza.ui.screen.onboarding.business.OnBoardingviewModel
import cd.wayupdotdev.uza.ui.theme.BlackGray
import cd.wayupdotdev.uza.ui.theme.Purple80
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalPagerApi::class)
@Destination
@Composable
fun OnBoardingScreen(navigator: DestinationsNavigator, viewModel: OnBoardingviewModel = hiltViewModel()) {

    val items = ArrayList<OnBoardingData>()

    items.add(
        OnBoardingData(
            R.raw.animation_lkxsrlrl,
            "Acheter des produits géniaux",
            "Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface."
        )
    )

    items.add(
        OnBoardingData(
            R.raw.animation_lkxsi8ju,
            "Livraison en un jour",
            "Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface."
        )
    )

    items.add(
        OnBoardingData(
            R.raw.animation_lkxsnh3w,
            "Un support client exceptionnel",
            "Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface."
        )
    )

    val pagerState = rememberPagerState(
        pageCount = items.size,
        initialOffscreenLimit = 2,
        infiniteLoop = false,
        initialPage = 0,
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(state = pagerState) { page ->
                OnBoardingPager(
                    item = items[page],
                )
            }
            PagerIndicator(items.size, pagerState.currentPage)
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            BottomSection(pagerState.currentPage){
                navigator.navigate(MainScreenDestination)
                viewModel.isOnboardingShow(true)
            }
        }
    }
}

@Composable
fun OnBoardingPager(
    item: OnBoardingData
) {
    val composistion = rememberLottieComposition(LottieCompositionSpec.RawRes(item.image))
    val progress by animateLottieCompositionAsState(
        composition = composistion.value,
        iterations = LottieConstants.IterateForever
    )

    Column(
        modifier = Modifier
            .padding(top = 60.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LottieAnimation(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
                .padding(16.dp),
            composition = composistion.value,
            progress = { progress }
        )

        Text(
            text = item.title,
            modifier = Modifier.padding(top = 50.dp),
            color = BlackGray,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        )

        Text(
            text = item.desc,
            modifier = Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp),
            color = Color.Gray,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

    }
}

@ExperimentalPagerApi
@Composable
fun rememberPagerState(
    @androidx.annotation.IntRange(from = 0) pageCount: Int,
    @androidx.annotation.IntRange(from = 0) initialPage: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) initialPageOffset: Float = 0f,
    @androidx.annotation.IntRange(from = 1) initialOffscreenLimit: Int = 1,
    infiniteLoop: Boolean = false
): PagerState = rememberSaveable(saver = PagerState.Saver) {
    PagerState(
        pageCount = pageCount,
        currentPage = initialPage,
        currentPageOffset = initialPageOffset,
        offscreenLimit = initialOffscreenLimit,
        infiniteLoop = infiniteLoop
    )
}

@Composable
fun PagerIndicator(size: Int, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 60.dp)
    ) {
        repeat(size) {
            Indicator(isSelected = it == currentPage)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(targetValue = if (isSelected) 25.dp else 10.dp, label = "")

    Box(
        modifier = Modifier
            .padding(1.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) Purple80 else Color.DarkGray.copy(alpha = 0.5f)
            )
    )
}

@Composable
fun BottomSection(currentPager: Int, onStartBtnClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = if (currentPager != 2) Arrangement.SpaceBetween else Arrangement.Center
    ) {

        if (currentPager == 2) {
            OutlinedButton(
                onClick = { onStartBtnClicked() },
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.Black,
                    contentColor = Purple80
                ),
                shape = RoundedCornerShape(50), // = 40% percent
            ) {
                Text(
                    text = "Commencer",
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                    color = Purple80,
                    fontWeight = FontWeight.Medium
                )
            }
        } else {
            SkipNextButton("Précédent", Modifier.padding(start = 20.dp))
            SkipNextButton("Suivant", Modifier.padding(end = 20.dp))
        }

    }
}



@Composable
fun SkipNextButton(text: String, modifier: Modifier) {
    Text(
        text = text, color = Color.DarkGray, modifier = modifier, fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    )
}