package com.rodrigoguerrero.warriornotes

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.rodrigoguerrero.warriornotes.ui.theme.WarriorNotesTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WarriorNotesApp {
                // TODO: add content here
            }
        }
    }
}

@Composable
fun WarriorNotesApp(content: @Composable () -> Unit) {
    WarriorNotesTheme {
        content()
    }
}

@ExperimentalFoundationApi
@Composable
fun NotesList(notes: MutableList<Note>) {
    WarriorNotesTheme {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 180.dp)
        ) {
            items(notes) { note ->
                Note(note)
            }
        }
    }
}

@Composable
fun Note(
    note: Note,
    modifier: Modifier = Modifier
) {
    NoteCard(
        elevation = 4.dp,
        modifier = modifier
            .size(
                width = 170.dp,
                height = 250.dp
            )
            .padding(
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        Column {
            Text(
                text = note.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxHeight()
            )
        }
    }
}

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp),
    color: Color = MaterialTheme.colors.primary,
    contentColor: Color = MaterialTheme.colors.onSurface,
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(elevation = elevation, shape = shape, clip = false)
            .zIndex(elevation.value)
            .then(if (border != null) Modifier.border(border, shape) else Modifier)
            .background(
                color = color,
                shape = shape
            )
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor, content = content)
    }
}

@ExperimentalFoundationApi
@Preview("Sample Note Card")
@Composable
fun SnackCardPreview() {
    val notes = mutableListOf<Note>()
    for (i in 1..10) {
        notes.add(Note("Note #$i", "This is the content for note #$i"))
    }

    WarriorNotesApp {
        NotesList(notes)
    }
}
