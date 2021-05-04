package com.alexyndrik.skyengtest.data.remote.model

import androidx.annotation.IntRange

data class Meaning(
    val id: String,
    val wordId: Int,
    @IntRange(from = 1, to = 6)
    val difficultyLevel: Int,
    val partOfSpeechCode: PartOfSpeechCode,
    val prefix: String,
    val text: String,
    val soundUrl: String,
    val transcription: String,
    val properties: Properties,
    val updatedAt: String,
    val mnemonics: String,
    val translation: Translation,
    val images: List<Image>,
    val definition: Definition,
    val examples: List<Example>,
    val meaningsWithSimilarTranslation: List<MeaningWithSimilarTranslation>,
    val alternativeTranslations: List<AlternativeTranslation>
)
