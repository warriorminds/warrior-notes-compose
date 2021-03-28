package com.rodrigoguerrero.warriornotes.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.warriornotes.WarriorNotesList
import com.rodrigoguerrero.warriornotes.data.Note
import com.rodrigoguerrero.warriornotes.data.Tag
import com.rodrigoguerrero.warriornotes.ui.theme.WarriorNotesTheme

@Composable
fun NotesList(notes: MutableList<Note>) {
    WarriorNotesTheme {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(all = 8.dp)
        ) {
            items(notes) { note ->
                NoteCard(note)
            }
        }
    }
}

@Composable
fun NoteCard(
    note: Note,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colors.onSurface,
    defaultColor: Color = if (isSystemInDarkTheme()) Color.Black else Color.White
) {
    val color = remember(note.id) {
        note.color ?: defaultColor
    }
    ItemCard(
        elevation = 4.dp,
        modifier = modifier
            .fillMaxWidth(),
        color = color,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.onSurface
        )
    ) {
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Column(modifier = Modifier.padding(all = 16.dp)) {
                Text(
                    text = note.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h6,
                    color = textColor
                )
                Text(
                    text = note.content,
                    style = MaterialTheme.typography.body1,
                    color = textColor
                )
            }
            TagList(note.tags, color)
        }
    }
}

@Preview("Sample Note")
@Composable
fun NotePreview() {
    WarriorNotesList {
        NoteCard(
            note = Note(
                id = 1,
                title = "Note title",
                content = "This is the content of the note.",
                tags = listOf(Tag("First tag"), Tag("Second tag"), Tag("Third Tag"))
            )
        )
    }
}

@ExperimentalFoundationApi
@Preview("Sample Note Card List")
@Composable
fun NoteCardListPreview() {
    val notes = mutableListOf<Note>()
    for (i in 1..10) {
        notes.add(
            Note(
                id = i,
                title = "Note #$i",
                content = "This is the content for note #$i",
                color = Color.Red
            )
        )
    }

    WarriorNotesList {
        NotesList(notes)
    }
}
