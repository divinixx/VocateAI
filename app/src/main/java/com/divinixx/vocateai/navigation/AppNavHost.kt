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
//import com.divinixx.vocateai.ui.career.CareerRoadmapScreen
//import com.divinixx.vocateai.ui.career.CareerRecommendationScreen

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
        
        composable(
            route = "mbti_result_screen/{mbtiType}",
            arguments = listOf(navArgument("mbtiType") { type = NavType.StringType })
        ) {
            val mbtiType = it.arguments?.getString("mbtiType") ?: "INFP"
            MBTIResultScreen(navController, mbtiType)
        }
        
        composable(
            route = "skill_assessment_screen/{mbtiType}",
            arguments = listOf(navArgument("mbtiType") { type = NavType.StringType })
        ) {
            val mbtiType = it.arguments?.getString("mbtiType") ?: "INFP"
            SkillAssessmentScreen(navController, mbtiType)
        }
        
        // Add the score screen route
//        composable(
//            route = "score_screen/{mbtiType}",
//            arguments = listOf(navArgument("mbtiType") { type = NavType.StringType })
//        ) {
//            val mbtiType = it.arguments?.getString("mbtiType") ?: "INFP"
//            ScoreScreen(navController, mbtiType)
//        }
        
        // Add career recommendation screen route
//        composable(
//            route = "career_recommendation_screen/{mbtiType}",
//            arguments = listOf(navArgument("mbtiType") { type = NavType.StringType })
//        ) {
//            val mbtiType = it.arguments?.getString("mbtiType") ?: "INFP"
//            CareerRecommendationScreen(navController, mbtiType)
//        }
        
        // Add career roadmap screen route
//        composable(
//            route = "career_roadmap_screen/{mbtiType}",
//            arguments = listOf(navArgument("mbtiType") { type = NavType.StringType })
//        ) {
//            val mbtiType = it.arguments?.getString("mbtiType") ?: "INFP"
//            CareerRoadmapScreen(navController, mbtiType)
//        }
    }
}