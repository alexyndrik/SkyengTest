package com.alexyndrik.skyengtest.data.remote.model

data class Meaning2(
    val id: String,
    val partOfSpeechCode: PartOfSpeechCode,
    val translation: Translation,
    val previewUrl: String,
    val imageUrl: String,
    val transcription: String,
    val soundUrl: String
)
