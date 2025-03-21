package com.divinixx.vocateai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.divinixx.vocateai.ui.theme.VocateAITheme
import com.divinixx.vocateai.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VocateAITheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    VocateAITheme {
        val navController = rememberNavController()
        AppNavHost(navController = navController)
    }
}

