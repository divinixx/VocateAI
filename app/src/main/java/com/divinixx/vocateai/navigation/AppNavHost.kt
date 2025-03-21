package com.divinixx.vocateai.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.divinixx.vocateai.ui.home.HomeScreen
import com.divinixx.vocateai.ui.theme.VocateAITheme

@Composable
fun AppNavHost(navController: NavHostController, startDestination: String = "home_screen") {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home_screen") {
            HomeScreen(navController)
        }
        
        composable("mbti_screen") {
            DummyScreen("MBTI Assessment Screen")
        }
        
        composable("interest_screen") {
            DummyScreen("Interest & Preferences Screen")
        }
        
        composable("skill_assessment_screen") {
            DummyScreen("Skill Assessment Screen")
        }
        
        composable("score_screen") {
            DummyScreen("Score & Personality Overview Screen")
        }
        
        composable("career_recommendation_screen") {
            DummyScreen("Smart Career Recommendation Screen")
        }
        
        composable("roadmap_screen") {
            DummyScreen("Roadmap Generator Screen")
        }
    }
}

@Composable
fun DummyScreen(screenName: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = screenName,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppNavHostPreview() {
    VocateAITheme {
        val navController = rememberNavController()
        AppNavHost(navController = navController)
    }
}