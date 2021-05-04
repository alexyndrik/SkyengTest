package com.alexyndrik.skyengtest.data.remote.model

data class Word(
    val id: Int,
    val text: String,
    val meanings: List<Meaning2>
)
