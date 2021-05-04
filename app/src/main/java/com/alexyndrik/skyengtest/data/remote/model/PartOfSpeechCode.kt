package com.alexyndrik.skyengtest.data.remote.model

enum class PartOfSpeechCode(val fullName: String) {
    n("noun"),
    v("verb"),
    j("adjective"),
    r("adverb"),
    prp("preposition"),
    prn("pronoun"),
    crd("cardinal number"),
    cjc("conjunction"),
    exc("interjection"),
    det("article"),
    abb("abbreviation"),
    x("particle"),
    ord("ordinal number"),
    md("modal verb"),
    ph("phrase"),
    phi("idiom")
}