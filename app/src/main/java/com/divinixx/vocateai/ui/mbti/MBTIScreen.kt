package com.divinixx.vocateai.ui.mbti

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.divinixx.vocateai.ui.theme.VocateAITheme

data class MBTIQuestion(
    val id: Int,
    val question: String,
    val optionA: String,
    val optionAValue: String,
    val optionB: String,
    val optionBValue: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MBTIScreen(navController: NavController) {
    // State for current question index
    var currentQuestionIndex by remember { mutableStateOf(0) }
    
    // State for storing answers (1 for A, 2 for B)
    val answers = remember { mutableStateListOf<Int?>().apply { 
        repeat(12) { add(null) } 
    }}
    
    // List of MBTI questions
    val questions = remember {
        listOf(
            MBTIQuestion(
                1,
                "At a party, you:",
                "Interact with many, including strangers",
                "E",
                "Interact with a few, known to you",
                "I"
            ),
            MBTIQuestion(
                2,
                "You are more:",
                "Realistic than speculative",
                "S",
                "Speculative than realistic",
                "N"
            ),
            MBTIQuestion(
                3,
                "Is it worse to:",
                "Have your head in the clouds",
                "T",
                "Be in a rut",
                "F"
            ),
            MBTIQuestion(
                4,
                "You are more impressed by:",
                "Principles",
                "T",
                "Emotions",
                "F"
            ),
            MBTIQuestion(
                5,
                "You are drawn more to:",
                "The structured and scheduled",
                "J",
                "The unstructured and unplanned",
                "P"
            ),
            MBTIQuestion(
                6,
                "You prefer to work:",
                "In teams, collaborating with others",
                "E",
                "Alone or in small, familiar groups",
                "I"
            ),
            MBTIQuestion(
                7,
                "You tend to choose:",
                "What is practical and works now",
                "S",
                "What is innovative and might work in the future",
                "N"
            ),
            MBTIQuestion(
                8,
                "When making decisions, you typically rely on:",
                "Logic and objective analysis",
                "T",
                "Personal values and how others will be affected",
                "F"
            ),
            MBTIQuestion(
                9,
                "You prefer environments that are:",
                "Organized with clear expectations",
                "J",
                "Flexible with room for spontaneity",
                "P"
            ),
            MBTIQuestion(
                10,
                "When solving problems, you prefer to:",
                "Follow established methods and procedures",
                "S",
                "Explore new approaches and possibilities",
                "N"
            ),
            MBTIQuestion(
                11,
                "You get more satisfaction from:",
                "Discussing ideas with others",
                "E",
                "Reflecting on ideas by yourself",
                "I"
            ),
            MBTIQuestion(
                12,
                "In your free time, you prefer to:",
                "Plan activities in advance",
                "J",
                "Be spontaneous and go with the flow",
                "P"
            )
        )
    }
    
    // Track MBTI scores
    val mbtiScores = remember { mutableStateMapOf(
        "E" to 0, "I" to 0,
        "S" to 0, "N" to 0,
        "T" to 0, "F" to 0,
        "J" to 0, "P" to 0
    )}
    
    // Function to calculate MBTI type based on scores
    fun calculateMBTIType(): String {
        // Calculate the final MBTI type based on the highest scores in each dimension
        val e = mbtiScores["E"] ?: 0
        val i = mbtiScores["I"] ?: 0
        val s = mbtiScores["S"] ?: 0
        val n = mbtiScores["N"] ?: 0
        val t = mbtiScores["T"] ?: 0
        val f = mbtiScores["F"] ?: 0
        val j = mbtiScores["J"] ?: 0
        val p = mbtiScores["P"] ?: 0
        
        val ei = if (e > i) "E" else "I"
        val sn = if (s > n) "S" else "N"
        val tf = if (t > f) "T" else "F"
        val jp = if (j > p) "J" else "P"
        
        return ei + sn + tf + jp
    }
    
    val currentQuestion = questions[currentQuestionIndex]
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MBTI Assessment") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Progress indicator
            LinearProgressIndicator(
                progress = (currentQuestionIndex + 1).toFloat() / questions.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
            
            // Question number
            Text(
                text = "Question ${currentQuestionIndex + 1} of ${questions.size}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Question text
            Text(
                text = currentQuestion.question,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            
            // Option A
            OptionButton(
                text = currentQuestion.optionA,
                isSelected = answers[currentQuestionIndex] == 1,
                onClick = { 
                    answers[currentQuestionIndex] = 1
                    mbtiScores[currentQuestion.optionAValue] = mbtiScores[currentQuestion.optionAValue]!! + 1
                }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Option B
            OptionButton(
                text = currentQuestion.optionB,
                isSelected = answers[currentQuestionIndex] == 2,
                onClick = { 
                    answers[currentQuestionIndex] = 2 
                    mbtiScores[currentQuestion.optionBValue] = mbtiScores[currentQuestion.optionBValue]!! + 1
                }
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Navigation buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Back button (hidden on first question)
                if (currentQuestionIndex > 0) {
                    Button(
                        onClick = { currentQuestionIndex-- },
                        modifier = Modifier.width(120.dp)
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Previous")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Previous")
                    }
                } else {
                    Spacer(modifier = Modifier.width(120.dp))
                }
                
                // Next button (for navigating through questions)
                if (currentQuestionIndex < questions.size - 1) {
                    Button(
                        onClick = {
                            if (answers[currentQuestionIndex] != null) {
                                currentQuestionIndex++
                            }
                        },
                        enabled = answers[currentQuestionIndex] != null,
                        modifier = Modifier.width(120.dp)
                    ) {
                        Text("Next")
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.Default.ArrowForward, contentDescription = "Next")
                    }
                } else {
                    // Submit button (only on the last question)
                    Button(
                        onClick = {
                            // Calculate MBTI type
                            val mbtiType = calculateMBTIType()
                            
                            // Navigate to result screen with the mbtiType parameter
                            navController.navigate("mbti_result_screen/$mbtiType")
                        },
                        enabled = answers[currentQuestionIndex] != null,
                        modifier = Modifier.width(120.dp)
                    ) {
                        Text("Submit")
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.Default.ArrowForward, contentDescription = "Submit")
                    }
                }
            }
        }
    }
}

@Composable
fun OptionButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) 
                MaterialTheme.colorScheme.primary 
            else 
                MaterialTheme.colorScheme.surfaceVariant,
            contentColor = if (isSelected) 
                MaterialTheme.colorScheme.onPrimary 
            else 
                MaterialTheme.colorScheme.onSurfaceVariant
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MBTIScreenPreview() {
    VocateAITheme {
        val navController = rememberNavController()
        MBTIScreen(navController = navController)
    }
}