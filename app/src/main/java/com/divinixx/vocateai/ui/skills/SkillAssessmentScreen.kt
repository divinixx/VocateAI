package com.divinixx.vocateai.ui.skills

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.divinixx.vocateai.ui.theme.VocateAITheme

data class Skill(val name: String, val category: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillAssessmentScreen(navController: NavController, mbtiType: String) {
    val scrollState = rememberScrollState()
    
    // State for user's selected skills
    val userSkills = remember { mutableStateListOf<Skill>() }
    
    // State for custom skill input
    var customSkillName by remember { mutableStateOf("") }
    var customSkillCategory by remember { mutableStateOf("") }
    
    // State for dropdown menus
    var isSkillCategoryExpanded by remember { mutableStateOf(false) }
    var isInterestExpanded by remember { mutableStateOf(false) }
    var isValueExpanded by remember { mutableStateOf(false) }
    
    // Selected values
    var selectedSkillCategory by remember { mutableStateOf("") }
    var selectedInterest by remember { mutableStateOf("") }
    var selectedValue by remember { mutableStateOf("") }
    
    // Predefined lists
    val skillCategories = listOf(
        "Web Development", 
        "Graphic Design", 
        "Video Editing", 
        "Programming", 
        "Data Analysis",
        "Digital Marketing",
        "Content Writing",
        "Photography",
        "Music Production",
        "3D Modeling",
        "UI/UX Design",
        "Mobile App Development",
        "Game Development",
        "Animation",
        "Social Media Management"
    )
    
    val interests = listOf(
        "Technology",
        "Arts",
        "Science",
        "Business",
        "Healthcare",
        "Education",
        "Entertainment",
        "Sports",
        "Environment",
        "Social Impact"
    )
    
    val values = listOf(
        "Creativity",
        "Innovation",
        "Leadership",
        "Teamwork",
        "Independence",
        "Growth",
        "Challenge",
        "Balance",
        "Security",
        "Recognition"
    )
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Skill Assessment") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back to Results")
                    }
                }
            )
        }
    )
    { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )
         {
            // MBTI Type Card - Show the user's MBTI type at the top
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Your Personality Type: $mbtiType",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "Now, let's build on your personality profile by adding your skills and interests.",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
            
            // Introduction Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Tell us about your skills",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "Select from our predefined categories or add your own skills to help us understand your strengths.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            
            // Skill Category Dropdown
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Skill Categories",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    ExposedDropdownMenuBox(
                        expanded = isSkillCategoryExpanded,
                        onExpandedChange = { isSkillCategoryExpanded = it }
                    ) {
                        OutlinedTextField(
                            value = selectedSkillCategory,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Select a skill category") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isSkillCategoryExpanded)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                        )
                        
                        ExposedDropdownMenu(
                            expanded = isSkillCategoryExpanded,
                            onDismissRequest = { isSkillCategoryExpanded = false }
                        ) {
                            skillCategories.forEach { category ->
                                DropdownMenuItem(
                                    text = { Text(category) },
                                    onClick = {
                                        selectedSkillCategory = category
                                        isSkillCategoryExpanded = false
                                        userSkills.add(Skill(category, "Skill Category"))
                                    }
                                )
                            }
                        }
                    }
                }
            }
            
            // Custom Skill Input
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Add Custom Skill",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    OutlinedTextField(
                        value = customSkillName,
                        onValueChange = { customSkillName = it },
                        label = { Text("Skill Name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    OutlinedTextField(
                        value = customSkillCategory,
                        onValueChange = { customSkillCategory = it },
                        label = { Text("Skill Category (optional)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Button(
                        onClick = {
                            if (customSkillName.isNotBlank()) {
                                val category = if (customSkillCategory.isBlank()) "Custom" else customSkillCategory
                                userSkills.add(Skill(customSkillName, category))
                                customSkillName = ""
                                customSkillCategory = ""
                            }
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Add Skill")
                    }
                }
            }
            
            // Interests Dropdown
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Interests",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    ExposedDropdownMenuBox(
                        expanded = isInterestExpanded,
                        onExpandedChange = { isInterestExpanded = it }
                    ) {
                        OutlinedTextField(
                            value = selectedInterest,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Select your interests") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isInterestExpanded)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                        )
                        
                        ExposedDropdownMenu(
                            expanded = isInterestExpanded,
                            onDismissRequest = { isInterestExpanded = false }
                        ) {
                            interests.forEach { interest ->
                                DropdownMenuItem(
                                    text = { Text(interest) },
                                    onClick = {
                                        selectedInterest = interest
                                        isInterestExpanded = false
                                        userSkills.add(Skill(interest, "Interest"))
                                    }
                                )
                            }
                        }
                    }
                }
            }
            // Values Dropdown
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Values",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    ExposedDropdownMenuBox(
                        expanded = isValueExpanded,
                        onExpandedChange = { isValueExpanded = it }
                    ) {
                        OutlinedTextField(
                            value = selectedValue,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Select your values") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isValueExpanded)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                        )
                        
                        ExposedDropdownMenu(
                            expanded = isValueExpanded,
                            onDismissRequest = { isValueExpanded = false }
                        ) {
                            values.forEach { value ->
                                DropdownMenuItem(
                                    text = { Text(value) },
                                    onClick = {
                                        selectedValue = value
                                        isValueExpanded = false
                                        userSkills.add(Skill(value, "Value"))
                                    }
                                )
                            }
                        }
                    }
                }
            }
            
            // Selected Skills Display
            if (userSkills.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Your Selected Skills & Preferences",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        userSkills.forEach { skill ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = skill.name,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Medium
                                    )
                                    Text(
                                        text = skill.category,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                
                                IconButton(
                                    onClick = { userSkills.remove(skill) }
                                ) {
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "Remove",
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                            Divider(modifier = Modifier.padding(vertical = 4.dp))
                        }
                    }
                }
            }
            
            // Update the Continue button's onClick handler
            Button(
                onClick = { 
                    // Navigate to the score screen with the MBTI type
                    navController.navigate("score_screen/$mbtiType") {
                        // This will remove the skill assessment screen from the back stack
                        // but keep the MBTI result screen so users can go back to it
                        popUpTo("mbti_result_screen/$mbtiType") { inclusive = false }
                    }   
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = "Continue to Career Recommendations",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// Add this at the end of the file
@Preview(showBackground = true)
@Composable
fun SkillAssessmentScreenPreview() {
    VocateAITheme {
        val navController = rememberNavController()
        SkillAssessmentScreen(navController = navController, mbtiType = "INFP")
    }
}