package com.example.infomaniakemilie.common

import android.text.Spanned
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

// Selection of shows I like
val listOfMyShowsId = listOf(67017, 41007, 6577, 2993, 37675, 55138, 170, 63236, 60169, 36196,
    36505, 59755, 64352, 35951, 38963, 28276, 28717, 31568, 1939, 8244, 69956, 63451, 65876)

/*
 * Function shameless took from : https://issuetracker.google.com/issues/139320238?pli=1
 */
fun spannableStringToAnnotatedString(text: CharSequence): AnnotatedString {
    return if (text is Spanned) {
        val spanStyles = mutableListOf<AnnotatedString.Range<SpanStyle>>()
        spanStyles.addAll(text.getSpans(0, text.length, UnderlineSpan::class.java).map {
            AnnotatedString.Range(
                SpanStyle(textDecoration = TextDecoration.Underline),
                text.getSpanStart(it),
                text.getSpanEnd(it)
            )
        })
        spanStyles.addAll(text.getSpans(0, text.length, StyleSpan::class.java).map {
            AnnotatedString.Range(
                SpanStyle(fontWeight = FontWeight.Bold),
                text.getSpanStart(it),
                text.getSpanEnd(it)
            )
        })
        AnnotatedString(text.toString(), spanStyles = spanStyles)
    } else {
        AnnotatedString(text.toString())
    }
}

/*
 * Choose a number (count) in a list
 */
fun <T> List<T>.takeRandom(count: Int): List<T> {
    require(count <= size) { "La taille spécifiée est supérieure à la taille de la liste." }

    val shuffledList = shuffled()
    return shuffledList.subList(0, count)
}