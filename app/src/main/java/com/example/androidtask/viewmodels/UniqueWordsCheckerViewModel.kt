package com.example.androidtask.viewmodels

import androidx.lifecycle.ViewModel

class UniqueWordsCheckerViewModel:ViewModel() {

    fun countUniqueWords(input: String): Map<String, Int> {
        val wordCountMap = mutableMapOf<String, Int>()

        // Remove punctuation and split the input into words
        val words = input.split(Regex("\\W+"))

        // Count the occurrences of each word (case-insensitive)
        for (word in words) {
            if (word.isNotBlank()) {
                val lowercaseWord = word.lowercase()
                wordCountMap[lowercaseWord] = wordCountMap.getOrDefault(lowercaseWord, 0) + 1
            }
        }

        return wordCountMap
    }

    fun main() {
        val input = "Rabbia and Aqeel are married. Rabbia is happy with Aqeel. And Aqeel love her alot."
        val wordCounts = countUniqueWords(input)
        println(wordCounts)
    }
}