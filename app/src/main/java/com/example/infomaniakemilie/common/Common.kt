package com.example.infomaniakemilie.common

import android.text.Spanned
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

/*
 * Fonction reprise ici : https://issuetracker.google.com/issues/139320238?pli=1
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
 * Prend un nombre (count) dans une liste
 */
fun <T> List<T>.takeRandom(count: Int): List<T> {
    require(count <= size) { "La taille spécifiée est supérieure à la taille de la liste." }

    val shuffledList = shuffled()
    return shuffledList.subList(0, count)
}

inline fun <T1 : Any, T2 : Any, R : Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}

inline fun <T1 : Any, T2 : Any, T3 : Any, R : Any> safeLet(p1: T1?, p2: T2?, p3: T3?, block: (T1, T2, T3) -> R?): R? {
    return if (p1 != null && p2 != null && p3 != null) block(p1, p2, p3) else null
}