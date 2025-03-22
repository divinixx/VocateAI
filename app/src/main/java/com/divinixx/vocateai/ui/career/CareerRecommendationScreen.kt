//package com.divinixx.vocateai.ui.career
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowForward
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//
//data class Career(
//    val title: String,
//    val description: String,
//    val matchPercentage: Int,
//    val skills: List<String>,
//    val education: String,
//    val salary: String
//)
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CareerRecommendationScreen(navController: NavController, mbtiType: String) {
//    // Mock data for career recommendations
//    val careers = remember {
//        listOf(
//            Career(
//                title = "Software Developer",
//                description = "Design, build, and maintain computer programs. Identify user needs, write code, and ensure software functions properly.",
//                matchPercentage = 95,
//                skills = listOf("Problem-solving", "Coding", "Logical thinking", "Attention to detail"),
//                education = "Bachelor's in Computer Science or equivalent experience",
//                salary = "$70,000 - $150,000"
//            ),
//            Career(
//                title = "Data Scientist",
//                description = "Analyze and interpret complex data to help organizations make better decisions using statistics, machine learning, and programming.",
//                matchPercentage = 88,
//                skills = listOf("Statistics", "Programming", "Machine learning", "Data visualization"),
//                education = "Master's in Data Science, Statistics, or related field",
//                salary = "$90,000 - $160,000"
//            ),
//            Career(
//                title = "UX/UI Designer",
//                description = "Create user-friendly interfaces for websites and applications, focusing on optimizing user experience through research and visual design.",
//                matchPercentage = 82,
//                skills = listOf("Visual design", "User research", "Prototyping", "Empathy"),
//                education = "Bachelor's in Design or equivalent portfolio",
//                salary = "$65,000 - $130,000"
//            ),
//            Career(
//                title = "Product Manager",
//                description = "Lead the development of products from conception to launch, balancing business needs with technical constraints and user experience.",
//                matchPercentage = 79,
//                skills = listOf("Leadership", "Strategic thinking", "Communication", "Technical understanding"),
//                education = "Bachelor's degree with business and technical experience",
//                salary = "$80,000 - $170,000"
//            )
//        )
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Career Recommendations") }
//            )
//        }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .padding(16.dp)
//                .verticalScroll(rememberScrollState()),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "Recommended Careers for $mbtiType",
//                style = MaterialTheme.typography.headlineSmall,
//                fontWeight = FontWeight.Bold,
//                textAlign = TextAlign.Center,
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//
//            Text(
//                text = "Based on your personality type and assessment results, these careers may be a good fit for you:",
//                style = MaterialTheme.typography.bodyLarge,
//                textAlign = TextAlign.Center,
//                modifier = Modifier.padding(bottom = 24.dp)
//            )
//
//            careers.forEach { career ->
//                CareerCard(career)
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Button(
//                onClick = {
//                    navController.navigate("career_roadmap_screen/$mbtiType")