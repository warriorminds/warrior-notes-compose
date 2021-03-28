package com.rodrigoguerrero.warriornotes.data

import androidx.compose.ui.graphics.Color

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val color: Color? = null,
    val tags: List<Tag> = listOf()
)
