package cd.wayupdotdev.uza.ui.screen.auth.view

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cd.wayupdotdev.uza.R
import cd.wayupdotdev.uza.destinations.ProfileScreenDestination
import cd.wayupdotdev.uza.ui.screen.auth.business.AuthState
import cd.wayupdotdev.uza.ui.screen.auth.business.AuthViewModel
import cd.wayupdotdev.uza.util.Constants
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AuthScreen(navigator: DestinationsNavigator, viewModel: AuthViewModel = hiltViewModel()) {

    val context = LocalContext.current
    BackHandler(enabled = true) {
        (context as? Activity)?.finish()
    }

    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                viewModel.register(account.idToken!!)
                Log.d("Login", "Launched")
            } catch (e: ApiException) {
                Log.e("LoginScreen", "Login with google failed e : ${e.message}")
            }
        }
    )

    LaunchedEffect(state) {
        when (state) {
            is AuthState.Success -> {
                navigator.navigate(ProfileScreenDestination)
            }
            is AuthState.Error -> {
                snackbarHostState.showSnackbar((state as AuthState.Error).errorMessage)
            }
            else -> {}
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.img_splash),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0x22FFFFFF),
                        Color(0xE40C0C0C)
                    ),
                    startY = 100.0f,
                    endY = 2100f
                )
            ), content = {})

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_uza_two),
                contentDescription = "logo white",
                modifier = Modifier
                    .size(200.dp, 100.dp)
                    .padding(start = 14.dp)
            )
            Text(
                text = "Acheter des produits géniaux",
                style = MaterialTheme.typography.body2.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                border = BorderStroke(1.dp, color = Color.White),
                onClick = {
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(Constants.Token)
//                        .requestIdToken(Buildconfig)
                        .requestEmail()
                        .build()
                    val googleSignInClient = GoogleSignIn.getClient(context, gso)
                    launcher.launch(googleSignInClient.signInIntent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.White
                ),
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_google_logo),
                            tint = Color.Unspecified,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Se connecter avec Google",
                            style = MaterialTheme.typography.button.copy(color = Color.Black)
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}