package com.divinixx.vocateai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.divinixx.vocateai.navigation.AppNavHost
import com.divinixx.vocateai.ui.theme.VocateAITheme
import com.divinixx.vocateai.ui.skills.SkillAssessmentScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VocateAITheme {
                VocateAIApp()
            }
        }
    }
}

@Composable
fun VocateAIApp() {
    val navController = rememberNavController()
    
    Scaffold { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavHost(navController = navController)
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

//@Composable
//fun VocateAINavHost(navController: NavHostController) {
//    NavHost(
//        navController = navController,
//        startDestination = "home_screen"
//    ) {
//        composable("home_screen") {
//            HomeScreen(navController)
//        }
//        composable("mbti_screen") {
//            MBTIScreen(navController)
//        }
//        composable("mbti_result_screen") {
//            MBTIResultScreen(navController)
//        }
//        composable("skill_assessment_screen") {
//            SkillAssessmentScreen(navController)
//        }
//        composable("score_screen") {
//            ScoreScreen()
//        }
//        // ... other screens ...
//    }
//}

