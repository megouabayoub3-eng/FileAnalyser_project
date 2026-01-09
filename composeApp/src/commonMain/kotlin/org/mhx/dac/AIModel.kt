package org.mhx.dac

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AIModel {
    // This class represents the AI model integration
    // In a real implementation, this would connect to an actual AI model
    
    suspend fun analyzeText(text: String): AIAnalysisResult = withContext(Dispatchers.Default) {
        // Perform analysis on the text
        val wordCount = text.split("\\s+".toRegex()).filter { it.isNotBlank() }.size
        val charCount = text.length
        val digitCount = text.count { it.isDigit() }
        val letterCount = text.count { it.isLetter() }
        val sentenceCount = text.count { it == '.' || it == '!' || it == '?' }
        
        // Placeholder sentiment analysis
        val sentiment = when {
            text.contains("good", ignoreCase = true) || text.contains("great", ignoreCase = true) || text.contains("excellent", ignoreCase = true) -> "Positive"
            text.contains("bad", ignoreCase = true) || text.contains("terrible", ignoreCase = true) || text.contains("awful", ignoreCase = true) -> "Negative"
            else -> "Neutral"
        }
        
        // Extract key topics (simple keyword matching)
        val keywords = mutableListOf<String>()
        if (text.contains("machine", ignoreCase = true)) keywords.add("Machine Learning")
        if (text.contains("data", ignoreCase = true)) keywords.add("Data Analysis")
        if (text.contains("algorithm", ignoreCase = true)) keywords.add("Algorithms")
        if (text.contains("ai", ignoreCase = true) || text.contains("artificial intelligence", ignoreCase = true)) keywords.add("AI")
        if (text.contains("programming", ignoreCase = true) || text.contains("code", ignoreCase = true)) keywords.add("Programming")
        
        AIAnalysisResult(
            text = text,
            wordCount = wordCount,
            characterCount = charCount,
            digitCount = digitCount,
            letterCount = letterCount,
            sentenceCount = sentenceCount,
            sentiment = sentiment,
            keyTopics = keywords.toList(),
            readabilityScore = calculateReadabilityScore(text)
        )
    }
    
    private fun calculateReadabilityScore(text: String): Double {
        val words = text.split("\\s+".toRegex()).filter { it.isNotBlank() }
        if (words.isEmpty()) return 0.0
        
        val sentences = text.count { it == '.' || it == '!' || it == '?' }.coerceAtLeast(1)
        val syllables = words.sumOf { countSyllables(it) }
        
        // Flesch Reading Ease formula (simplified)
        // Score = 206.835 - (1.015 × ASL) - (84.6 × ASW)
        // ASL = average sentence length (words/sentences)
        // ASW = average number of syllables per word (syllables/words)
        val asl = words.size.toDouble() / sentences
        val asw = syllables.toDouble() / words.size.coerceAtLeast(1)
        
        val score = 206.835 - (1.015 * asl) - (84.6 * asw)
        return score.coerceIn(0.0, 100.0)
    }
    
    private fun countSyllables(word: String): Int {
        var wordLower = word.lowercase().trim { it <= ' ' }
        if (wordLower.isEmpty()) return 0
        
        var syllableCount = 0
        var prevWasVowel = false
        
        val vowels = "aeiouy"
        for (i in wordLower.indices) {
            val isVowel = vowels.contains(wordLower[i])
            if (isVowel && !prevWasVowel) {
                syllableCount++
            }
            prevWasVowel = isVowel
        }
        
        // Subtract 1 for silent 'e' at the end
        if (wordLower.endsWith("e") && syllableCount > 1) {
            syllableCount--
        }
        
        // Add 1 for words ending in 'le' after a consonant
        if (wordLower.length >= 2 && wordLower.endsWith("le") && !vowels.contains(wordLower[wordLower.length - 3])) {
            syllableCount++
        }
        
        return syllableCount.coerceAtLeast(1)
    }
}

data class AIAnalysisResult(
    val text: String,
    val wordCount: Int,
    val characterCount: Int,
    val digitCount: Int,
    val letterCount: Int,
    val sentenceCount: Int,
    val sentiment: String,
    val keyTopics: List<String>,
    val readabilityScore: Double
)