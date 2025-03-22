package com.divinixx.vocateai.ui.mbti

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.divinixx.vocateai.ui.theme.VocateAITheme

// Define the data class first
data class MBTIPersonalityInfo(
    val type: String,
    val title: String,
    val description: String,
    val strengths: List<String>,
    val careerMatches: List<String>
)

// Define the function before it's used
fun getMBTIPersonalityInfo(mbtiType: String): MBTIPersonalityInfo {
    return when (mbtiType) {
        "INTJ" -> MBTIPersonalityInfo(
            "INTJ",
            "The Architect",
            "INTJs are analytical problem-solvers, eager to improve systems and processes with their innovative ideas. They have a talent for seeing possibilities for improvement, whether at work, at home, or in themselves.",
            listOf(
                "Strategic thinkers with a clear vision",
                "Independent and decisive",
                "Open-minded to new ideas",
                "High standards and determination",
                "Creative and innovative"
            ),
            listOf(
                "Scientist or researcher",
                "Software engineer or programmer",
                "Systems analyst",
                "Business strategist",
                "Architect or designer"
            )
        )
        "INTP" -> MBTIPersonalityInfo(
            "INTP",
            "The Logician",
            "INTPs are innovative inventors with an unquenchable thirst for knowledge. They are known for their brilliant theories and unrelenting logic, which makes them both fascinating and intimidating.",
            listOf(
                "Analytical and logical thinking",
                "Original and creative ideas",
                "Open-minded approach to problems",
                "Objective and rational perspective",
                "Independent and autonomous work style"
            ),
            listOf(
                "Computer programmer or software developer",
                "Mathematician or statistician",
                "University professor",
                "Research scientist",
                "Systems analyst"
            )
        )
        "ENTJ" -> MBTIPersonalityInfo(
            "ENTJ",
            "The Commander",
            "ENTJs are strategic leaders, motivated to organize change. They are quick to see inefficiency and conceptualize new solutions, and enjoy developing long-range plans to accomplish their vision.",
            listOf(
                "Natural leaders who take charge",
                "Strategic and long-term thinkers",
                "Efficient and organized",
                "Charismatic and confident",
                "Decisive decision-makers"
            ),
            listOf(
                "Executive or business manager",
                "Management consultant",
                "Lawyer or judge",
                "Financial analyst",
                "Entrepreneur"
            )
        )
        "ENTP" -> MBTIPersonalityInfo(
            "ENTP",
            "The Debater",
            "ENTPs are inspired innovators, motivated to find new solutions to intellectually challenging problems. They are curious and clever, and seek to understand the people, systems, and principles that surround them.",
            listOf(
                "Quick thinkers and problem solvers",
                "Excellent brainstormers",
                "Enthusiastic and energetic",
                "Excellent communicators",
                "Knowledgeable and versatile"
            ),
            listOf(
                "Entrepreneur or business developer",
                "Creative director",
                "Lawyer or political consultant",
                "Systems analyst",
                "Marketing strategist"
            )
        )
        "INFJ" -> MBTIPersonalityInfo(
            "INFJ",
            "The Advocate",
            "INFJs are creative nurturers with a strong sense of personal integrity and a drive to help others realize their potential. They have a talent for helping others with complex problems.",
            listOf(
                "Deep sense of idealism and integrity",
                "Creative and artistic",
                "Insightful about others",
                "Decisive and determined",
                "Altruistic and compassionate"
            ),
            listOf(
                "Counselor or therapist",
                "Teacher or professor",
                "Writer or editor",
                "HR manager",
                "Non-profit director"
            )
        )
        "INFP" -> MBTIPersonalityInfo(
            "INFP",
            "The Mediator",
            "INFPs are imaginative idealists, guided by their own core values and beliefs. They are sensitive, caring, and compassionate, and deeply concerned with personal growth and fostering meaningful relationships.",
            listOf(
                "Empathetic and compassionate",
                "Creative and imaginative",
                "Open-minded and flexible",
                "Passionate and enthusiastic",
                "Dedicated to their values"
            ),
            listOf(
                "Writer or editor",
                "Counselor or psychologist",
                "Teacher or professor",
                "Artist or graphic designer",
                "Social worker"
            )
        )
        "ENFJ" -> MBTIPersonalityInfo(
            "ENFJ",
            "The Protagonist",
            "ENFJs are charismatic and inspiring leaders, able to mesmerize their listeners. They are typically focused on helping others and providing guidance, coaching, or counseling to reach their potential.",
            listOf(
                "Natural leaders and motivators",
                "Charismatic and persuasive",
                "Empathetic and caring",
                "Organized and decisive",
                "Reliable and trustworthy"
            ),
            listOf(
                "Teacher or professor",
                "HR manager or trainer",
                "Counselor or therapist",
                "Marketing or public relations specialist",
                "Non-profit director"
            )
        )
        "ENFP" -> MBTIPersonalityInfo(
            "ENFP",
            "The Campaigner",
            "ENFPs are enthusiastic, creative, and sociable free spirits, who can always find a reason to smile. They're people-centered creators with a focus on possibilities and a contagious enthusiasm for new ideas, people and activities.",
            listOf(
                "Enthusiastic and creative",
                "Excellent people skills",
                "Strong communication abilities",
                "Curious and observant",
                "Passionate and energetic"
            ),
            listOf(
                "Journalist or reporter",
                "Actor or entertainer",
                "Marketing or advertising professional",
                "Event planner",
                "Human resources specialist"
            )
        )
        "ISTJ" -> MBTIPersonalityInfo(
            "ISTJ",
            "The Logistician",
            "ISTJs are responsible organizers, driven to create and enforce order within systems and institutions. They are neat and orderly, inside and out, and tend to have a procedure for everything they do.",
            listOf(
                "Practical and fact-minded",
                "Reliable and responsible",
                "Organized and methodical",
                "Loyal and devoted",
                "Calm and steady"
            ),
            listOf(
                "Accountant or financial analyst",
                "Military or police officer",
                "Judge or lawyer",
                "Doctor or dentist",
                "Office manager or administrator"
            )
        )
        "ISFJ" -> MBTIPersonalityInfo(
            "ISFJ",
            "The Defender",
            "ISFJs are industrious caretakers, loyal to traditions and organizations. They are practical, compassionate, and caring, and are motivated to provide for others and protect them from the perils of life.",
            listOf(
                "Supportive and reliable",
                "Observant and attentive to detail",
                "Patient and devoted",
                "Responsible and conscientious",
                "Loyal and committed"
            ),
            listOf(
                "Nurse or healthcare worker",
                "Elementary school teacher",
                "Social worker",
                "Administrative assistant",
                "Customer service representative"
            )
        )
        "ESTJ" -> MBTIPersonalityInfo(
            "ESTJ",
            "The Executive",
            "ESTJs are excellent administrators, unsurpassed at managing things or people. They live in a world of facts and concrete needs, and they value tradition, security, and clear hierarchies.",
            listOf(
                "Practical and realistic",
                "Dependable and responsible",
                "Loyal and hardworking",
                "Traditional and structured",
                "Strong leadership abilities"
            ),
            listOf(
                "Business manager or executive",
                "Military or police officer",
                "Judge or lawyer",
                "Financial officer",
                "School administrator"
            )
        )
        "ESFJ" -> MBTIPersonalityInfo(
            "ESFJ",
            "The Consul",
            "ESFJs are extraordinarily caring, social, and popular people, always eager to help. They are warm-hearted and conscientious, placing the needs of others over their own.",
            listOf(
                "Strong people skills",
                "Practical and traditional",
                "Loyal and reliable",
                "Organized and methodical",
                "Supportive and nurturing"
            ),
            listOf(
                "Healthcare administrator",
                "Elementary school teacher",
                "Sales representative",
                "Human resources specialist",
                "Social worker"
            )
        )
        "ISTP" -> MBTIPersonalityInfo(
            "ISTP",
            "The Virtuoso",
            "ISTPs are observant artisans with an understanding of mechanics and an interest in troubleshooting. They approach their environments with a flexible logic, looking for practical solutions to problems at hand.",
            listOf(
                "Optimistic and energetic",
                "Creative and practical",
                "Spontaneous and rational",
                "Independent and self-directed",
                "Adaptable and resourceful"
            ),
            listOf(
                "Engineer or mechanic",
                "Pilot or driver",
                "Carpenter or craftsperson",
                "Forensic scientist",
                "Emergency medical technician"
            )
        )
        "ISFP" -> MBTIPersonalityInfo(
            "ISFP",
            "The Adventurer",
            "ISFPs are gentle caretakers who live in the present moment and enjoy their surroundings with cheerful, low-key enthusiasm. They are flexible, spontaneous, and prefer to go with the flow to enjoy what life has to offer.",
            listOf(
                "Sensitive and caring",
                "Humble and adaptable",
                "Artistic and creative",
                "Passionate and enthusiastic",
                "Live in the present moment"
            ),
            listOf(
                "Artist or designer",
                "Musician or composer",
                "Chef or baker",
                "Veterinarian or animal care",
                "Fashion designer"
            )
        )
        "ESTP" -> MBTIPersonalityInfo(
            "ESTP",
            "The Entrepreneur",
            "ESTPs are energetic thrillseekers who are at their best when putting out fires, whether literal or metaphorical. They bring a sense of dynamic energy to their interactions with others and the world around them.",
            listOf(
                "Bold and spontaneous",
                "Rational and practical",
                "Original and perceptive",
                "Direct and sociable",
                "Energetic and active"
            ),
            listOf(
                "Entrepreneur or business owner",
                "Sales or marketing professional",
                "Athlete or sports coach",
                "Paramedic or firefighter",
                "Construction manager"
            )
        )
        "ESFP" -> MBTIPersonalityInfo(
            "ESFP",
            "The Entertainer",
            "ESFPs are vivacious entertainers who charm and engage those around them. They are spontaneous, energetic, and fun-loving, and they live for the moment, enjoying what life has to offer.",
            listOf(
                "Enthusiastic and spontaneous",
                "Excellent people skills",
                "Practical and observant",
                "Adaptable and resourceful",
                "Live in the present moment"
            ),
            listOf(
                "Event planner",
                "Sales representative",
                "Tour guide or travel agent",
                "Performer or entertainer",
                "Public relations specialist"
            )
        )
        else -> MBTIPersonalityInfo(
            mbtiType,
            "Personality Type",
            "Your personality type combines several traits that influence how you perceive the world and make decisions.",
            listOf(
                "Unique combination of traits",
                "Personal strengths based on your type",
                "Individual approach to problem-solving",
                "Distinctive communication style",
                "Special perspective on the world"
            ),
            listOf(
                "Careers that match your analytical abilities",
                "Roles that utilize your communication style",
                "Positions that value your decision-making approach",
                "Jobs that benefit from your perspective",
                "Fields that appreciate your unique strengths"
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MBTIResultScreen(navController: NavController, mbtiType: String) {
    val personalityInfo = getMBTIPersonalityInfo(mbtiType)
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your MBTI Result") }
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
            // MBTI Type Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                     Text(
                        text = "Your Personality Type",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                     )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                     Text(
                        text = mbtiType,
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                     )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                     Text(
                        text = personalityInfo.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                     )
                }
             } // Added missing closing brace for the first Card
            
            // Personality Description
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = personalityInfo.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            
            // Strengths
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Strengths",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    personalityInfo.strengths.forEach { strength ->
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Text(
                                text = "• ",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = strength,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
            
            // Career Matches
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Potential Career Matches",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    personalityInfo.careerMatches.forEach { career ->
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Text(
                                text = "• ",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = career,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Continue Button - FIXED NAVIGATION
            Button(
                onClick = { 
                // Correct navigation with parameter
                navController.navigate("skill_assessment_screen/$mbtiType")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = "Continue to Interest Assessment",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Continue"
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Add this at the bottom of your content column
            Spacer(modifier = Modifier.height(24.dp))
            
            // REMOVE THIS ERRONEOUS NAVIGATION CALL (line 362)
            // navController.navigate("skill_assessment_screen/$mbtiType")
        }
    }
}

@Preview(showBackground = true, heightDp = 900, widthDp = 450)
@Composable
fun MBTIResultScreenPreview() {
    VocateAITheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            MBTIResultScreen(
                navController = rememberNavController(),
                mbtiType = "INTJ"
            )
        }
    }
}