package com.alexyndrik.skyengtest.data.remote.model

data class MeaningWithSimilarTranslation(
    val meaningId: Int,
    val frequencyPercent: String,
    val partOfSpeechAbbreviation: String,
    val translation: Translation
)
