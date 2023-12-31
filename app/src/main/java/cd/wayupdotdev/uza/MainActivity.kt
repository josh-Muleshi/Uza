package cd.wayupdotdev.uza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cd.wayupdotdev.uza.navigation.MainScreen
import cd.wayupdotdev.uza.navigation.SetupNavGraph
import cd.wayupdotdev.uza.ui.theme.UzaTheme
import cd.wayupdotdev.uza.ui.viewModel.SplashViewModel
import cd.wayupdotdev.uza.ui.viewModel.business.MainState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition{viewModel.isLoading.value}

        setContent {
            UzaTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    val isShow by viewModel.isShow.collectAsState()

                    when(isShow){
                        is MainState.Success -> {
                            if ((isShow as MainState.Success).isShow) {
                                MainScreen()
                            } else {
                                SetupNavGraph()
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}
