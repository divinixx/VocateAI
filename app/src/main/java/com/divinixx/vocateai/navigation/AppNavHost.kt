package com.divinixx.vocateai.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.divinixx.vocateai.ui.home.HomeScreen
import com.divinixx.vocateai.ui.mbti.MBTIScreen
import com.divinixx.vocateai.ui.mbti.MBTIResultScreen
import com.divinixx.vocateai.ui.skills.SkillAssessmentScreen
//import com.divinixx.vocateai.ui.score.ScoreScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home_screen"
    ) {
        composable("home_screen") {
            HomeScreen(navController)
        }
        
        composable("mbti_screen") {
            MBTIScreen(navController)
        }
        
        // Add the MBTI result screen route
        composable(
            route = "mbti_result_screen/{mbtiType}",
            arguments = listOf(navArgument("mbtiType") { type = NavType.StringType })
        ) {
            val mbtiType = it.arguments?.getString("mbtiType") ?: "INFP"
            MBTIResultScreen(navController, mbtiType)
        }
        
        // Keep only one definition for the skill assessment screen
        composable(
            route = "skill_assessment_screen/{mbtiType}",
            arguments = listOf(navArgument("mbtiType") { type = NavType.StringType })
        ) {
            val mbtiType = it.arguments?.getString("mbtiType") ?: "INFP" // Default value if null
            SkillAssessmentScreen(navController, mbtiType)
        }
        
//        composable("score_screen") {
//            ScoreScreen()
//        }
    }
}