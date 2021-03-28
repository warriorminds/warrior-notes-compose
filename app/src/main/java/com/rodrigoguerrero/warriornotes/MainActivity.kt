package com.rodrigoguerrero.warriornotes

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.rodrigoguerrero.warriornotes.data.Note
import com.rodrigoguerrero.warriornotes.data.Tag
import com.rodrigoguerrero.warriornotes.ui.components.NotesList
import com.rodrigoguerrero.warriornotes.ui.theme.WarriorNotesTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val notes = mutableListOf<Note>()
            for (i in 1..10) {
                notes.add(
                    Note(
                        id = i,
                        title = "Note #$i",
                        content = "This is the content for note #$i",
                        tags = listOf(
                            Tag("First"),
                            Tag("Tag"),
                            Tag("Whatever"),
                            Tag("Project"),
                            Tag("Last"),
                            Tag("First"),
                            Tag("Tag"),
                            Tag("Whatever"),
                            Tag("Project"),
                            Tag("Last")
                        )
                    )
                )
            }
            WarriorNotesList {
                NotesList(notes = notes)
            }
        }
    }
}

@Composable
fun WarriorNotesList(content: @Composable () -> Unit) {
    WarriorNotesTheme {
        Scaffold {
            content()
        }
    }
}

