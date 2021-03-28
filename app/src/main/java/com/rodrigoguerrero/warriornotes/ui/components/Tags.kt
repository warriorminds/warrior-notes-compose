package com.rodrigoguerrero.warriornotes.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.warriornotes.WarriorNotesList
import com.rodrigoguerrero.warriornotes.data.Tag

@Composable
fun TagCard(
    tag: Tag,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colors.onSurface
) {
    ItemCard(
        modifier = modifier,
        color = backgroundColor,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.onSurface
        )
    ) {
        Text(
            text = tag.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.caption,
            color = textColor,
            modifier = Modifier.padding(all = 8.dp)
        )
    }
}

@Composable
fun TagList(
    tags: List<Tag>,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    if (tags.isNotEmpty()) {
        Row(
            modifier
                .horizontalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            tags.forEach { tag ->
                Spacer(modifier = Modifier.width(8.dp))
                TagCard(tag, backgroundColor)
            }
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Preview
@Composable
fun TagPreview() {
    WarriorNotesList {
        TagCard(
            Tag("Tag name"),
            backgroundColor = Color.Black
        )
    }
}

@Preview
@Composable
fun TagListPreview() {
    WarriorNotesList {
        TagList(
            tags = listOf(
                Tag("First"),
                Tag("Second"),
                Tag("Third")
            ),
            backgroundColor = Color.Black
        )
    }
}