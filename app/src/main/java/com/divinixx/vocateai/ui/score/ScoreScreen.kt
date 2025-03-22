//package com.divinixx.vocateai.ui.score
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
//import kotlinx.coroutines.launch
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.GET
//import retrofit2.http.Path
//
//// Data classes for API responses
//data class CareerScore(
//    val mbtiScore: Int,
//    val skillScore: Int,
//    val interestScore: Int,
//    val valueScore: Int,
//    val totalScore: Int
//)
//
//data class CareerRecommendation(
//    val title: String,
//    val description: String,
//    val matchPercentage: Int,
//    val roadmap: List<String>
//)
//
//// Retrofit API interface
//interface CareerApi {
//
//
//    @GET("careers/{mbtiType}")
//    suspend fun getCareerRecommendations(@Path("mbtiType") mbtiType: String): List<CareerRecommendation>
//
//    @GET("scores/{mbtiType}")
//    suspend fun getCareerScores(@Path("mbtiType") mbtiType: String): CareerScore
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ScoreScreen(navController: NavController, mbtiType: String) {
//    val coroutineScope = rememberCoroutineScope()
//
//    // State for API data
//    var careerScore by remember { mutableStateOf<CareerScore?>(null) }
//    var recommendations by remember { mutableStateOf<List<CareerRecommendation>>(emptyList()) }
//    var isLoading by remember { mutableStateOf(true) }
//    var error by remember { mutableStateOf<String?>(null) }
//
//    // Create Retrofit instance
//    val retrofit = remember {
//        Retrofit.Builder()
//            .baseUrl("https://your-api-url.com/api/") // Replace with your actual API URL
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    val careerApi = remember { retrofit.create(CareerApi::class.java) }
//
//    // Fetch data when the screen is first displayed
//    LaunchedEffect(mbtiType) {
//        isLoading = true
//        error = null
//
//        try {
//            coroutineScope.launch {
//                // Fetch career scores and recommendations
//                careerScore = careerApi.getCareerScores(mbtiType)
//                recommendations = careerApi.getCareerRecommendations(mbtiType)
//                isLoading = false
//            }
//        } catch (e: Exception) {
//            error = "Failed to load data: ${e.message}"
//            isLoading = false
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Your Career Profile") }
//            )
//        }
//    ) { paddingValues ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//        ) {
//            if (isLoading) {
//                CircularProgressIndicator(
//                    modifier = Modifier.align(Alignment.Center)
//                )
//            } else if (error != null) {
//                Text(
//                    text = error ?: "Unknown error occurred",
//                    color = MaterialTheme.colorScheme.error,
//                    modifier = Modifier
//                        .align(Alignment.Center)
//                        .padding(16.dp)
//                )
//            } else {
//                // Display scores and recommendations
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp)
//                        .verticalScroll(rememberScrollState()),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    // Personality Type Card
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 8.dp),
//                        shape = RoundedCornerShape(16.dp)
//                    ) {
//                        Column(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(16.dp),
//                            horizontalAlignment = Alignment.CenterHorizontally
//                        ) {
//                            Text(
//                                text = "Your Personality Type",
//                                style = MaterialTheme.typography.titleMedium,
//                                fontWeight = FontWeight.Bold
//                            )
//
//                            Spacer(modifier = Modifier.height(8.dp))
//
//                            Text(
//                                text = mbtiType,
//                                style = MaterialTheme.typography.displaySmall,
//                                fontWeight = FontWeight.Bold,
//                                color = MaterialTheme.colorScheme.primary
//                            )
//                        }
//                    }
//
//                    // Score Card
//                    careerScore?.let { score ->
//                        Card(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(vertical = 8.dp),
//                            shape = RoundedCornerShape(16.dp)
//                        ) {
//                            Column(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(16.dp)
//                            ) {
//                                Text(
//                                    text = "Your Career Match Scores",
//                                    style = MaterialTheme.typography.titleMedium,
//                                    fontWeight = FontWeight.Bold
//                                )
//
//                                Spacer(modifier = Modifier.height(16.dp))
//
//                                ScoreItem("MBTI Compatibility", score.mbtiScore)
//                                ScoreItem("Skills Alignment", score.skillScore)
//                                ScoreItem("Interest Match", score.interestScore)
//                                ScoreItem("Values Alignment", score.valueScore)
//
//                                Divider(modifier = Modifier.padding(vertical = 8.dp))
//
//                                ScoreItem("Overall Match", score.totalScore, true)
//                            }
//                        }
//                    }
//
//                    // Career Recommendations
//                    Text(
//                        text = "Recommended Careers",
//                        style = MaterialTheme.typography.titleLarge,
//                        fontWeight = FontWeight.Bold,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 16.dp),
//                        textAlign = TextAlign.Center
//                    )
//
//                    recommendations.forEach { recommendation ->
//                        CareerRecommendationCard(recommendation)
//                    }
//
//                    Spacer(modifier = Modifier.height(24.dp))
//
//                    // Continue Button
//                    Button(
//                        onClick = {
//                            navController.navigate("career_roadmap_screen/$mbtiType")
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(56.dp),
//                        shape = RoundedCornerShape(28.dp)
//                    ) {
//                        Text(
//                            text = "View Detailed Career Roadmap",
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Bold
//                        )
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Icon(
//                            imageVector = Icons.Default.ArrowForward,
//                            contentDescription = "Continue"
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ScoreItem(label: String, score: Int, isTotal: Boolean = false) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 4.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = label,
//            style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyLarge,
//            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal
//        )
//
//        Text(
//            text = "$score%",
//            style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyLarge,
//            fontWeight = FontWeight.Bold,
//            color = if (isTotal) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
//        )
//    }
//}
//
//@Composable
//fun CareerRecommendationCard(recommendation: CareerRecommendation) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp),
//        shape = RoundedCornerShape(16.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = recommendation.title,
//                    style = MaterialTheme.typography.titleMedium,
//                    fontWeight = FontWeight.Bold
//                )
//
//                Card(
//                    shape = RoundedCornerShape(16.dp),
//                    colors = CardDefaults.cardColors(
//                        containerColor = MaterialTheme.colorScheme.primaryContainer
//                    )
//                ) {
//                    Text(
//                        text = "${recommendation.matchPercentage}% Match",
//                        style = MaterialTheme.typography.bodyMedium,
//                        fontWeight = FontWeight.Bold,
//                        color = MaterialTheme.colorScheme.onPrimaryContainer,
//                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = recommendation.description,
//                style = MaterialTheme.typography.bodyMedium
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Text(
//                text = "Career Path:",
//                style = MaterialTheme.typography.bodyMedium,
//                fontWeight = FontWeight.Bold
//            )
//
//            recommendation.roadmap.forEachIndexed { index, step ->
//                Row(
//                    modifier = Modifier.padding(vertical = 4.dp)
//                ) {
//                    Text(
//                        text = "${index + 1}. ",
//                        style = MaterialTheme.typography.bodyMedium,
//                        fontWeight = FontWeight.Bold
//                    )
//                    Text(
//                        text = step,
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//            }
//        }
//    }
//}