package com.example.infomaniakemilie.common

import android.text.Spanned
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

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