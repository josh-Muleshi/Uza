package cd.wayupdotdev.uza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import cd.wayupdotdev.uza.navigation.AddPostScreen
import cd.wayupdotdev.uza.ui.theme.UzaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPostActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UzaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AddPostScreen()
                }
            }
        }
    }
}