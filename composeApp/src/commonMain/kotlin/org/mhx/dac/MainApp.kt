package org.mhx.dac

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun MainApp() {
    var text by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "AI-Powered File Analyzer",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter text to analyze") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                scope.launch {
                    // Simulate AI analysis
                    result = performAIAnalysis(text)
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Analyze with AI")
        }

        if (result.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = result,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

private val aiModel = AIModel()

// AI analysis function that uses our AIModel class
suspend fun performAIAnalysis(input: String): String {
    // This function now uses the AIModel class for more sophisticated analysis
    if (input.isEmpty()) {
        return "Please enter text to analyze"
    }
    
    val result = aiModel.analyzeText(input)
    return buildString {
        append("AI Analysis Result:\n\n")
        append("- Text contains ${result.characterCount} characters\n")
        append("- Word count: ${result.wordCount}\n")
        append("- Letter count: ${result.letterCount}\n")
        append("- Digit count: ${result.digitCount}\n")
        append("- Sentence count: ${result.sentenceCount}\n")
        append("- Estimated readability score: ${"%.2f".format(result.readabilityScore)}\n")
        append("- Sentiment: ${result.sentiment}\n")
        append("- Key topics: ${if (result.keyTopics.isEmpty()) "None detected" else result.keyTopics.joinToString(", ")}\n")
    }
}