package com.divinixx.vocateai.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class FeatureItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val featureItems = listOf(
        FeatureItem(
            "MBTI Assessment",
            "Discover your personality type and traits",
            Icons.Default.AccountBox,  // Changed from Psychology to AccountBox
            "mbti_screen"
        ),
        FeatureItem(
            "Interest & Preferences",
            "Explore your interests, values, philosophies, and goals",
            Icons.Default.Favorite,
            "interest_screen"
        ),
        FeatureItem(
            "Skill Assessment",
            "Evaluate your technical, soft, and other skills",
            Icons.Default.Build,
            "skill_assessment_screen"
        ),
        FeatureItem(
            "Score & Personality Overview",
            "View your comprehensive assessment results",
            Icons.Default.Star, // Changed from Assessment to Star
            "score_screen"
        ),
        FeatureItem(
            "Smart Career Recommendation",
            "Get AI-powered career suggestions based on your profile",
            Icons.Default.Star,  // Changed from WorkOutline to Star
            "career_recommendation_screen"
        ),
        FeatureItem(
            "Roadmap Generator",
            "Create a personalized career development plan",
            Icons.Default.List,  // Changed from Map to List
            "roadmap_screen"
        )
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Welcome to Kiosk App",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(featureItems) { item ->
                FeatureCard(
                    featureItem = item,
                    onClick = { navController.navigate(item.route) }
                )
            }
        }
    }
}

@Composable
fun FeatureCard(featureItem: FeatureItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = featureItem.icon,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 16.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = featureItem.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = featureItem.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Navigate",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}