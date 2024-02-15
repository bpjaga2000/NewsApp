package `in`.bpj4.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import `in`.bpj4.newsapp.presentation.navgraph.NavGraph
import `in`.bpj4.newsapp.ui.theme.NewsAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewmodel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewmodel.splashCondition
            }
        }
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                val a = isSystemInDarkTheme()
                LaunchedEffect(key1 = a) {
                    if (a)
                        enableEdgeToEdge(
                            statusBarStyle = SystemBarStyle.dark(
                                Color.LightGray.toArgb()
                            ),
                            navigationBarStyle = SystemBarStyle.dark(
                                Color.LightGray.toArgb()
                            )
                        )
                    else
                        enableEdgeToEdge(
                            statusBarStyle = SystemBarStyle.light(
                                Color.White.toArgb(),
                                Color.LightGray.toArgb()
                            ),
                            navigationBarStyle = SystemBarStyle.light(
                                Color.White.toArgb(),
                                Color.LightGray.toArgb()
                            )
                        )
                }
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                    val startDestination = viewmodel.startDestination
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}