package com.example.booklibraryapplication.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.booklibraryapplication.model.Book

/**
 * BookRow - Reusable composable for displaying a single book
 *
 * Follows the pattern from Practical 5 (ContactRow)
 *
 * Additional features from Practical 6:
 *  - Checkbox for toggling read status (like ShoppingItemRow)
 *  - Delete icon with IconButton
 */

@Composable
fun BookRow(
    book: Book,
    onToggleRead: () -> Unit,
    onDeleteClick: () -> Unit,
    onBookClick: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onBookClick }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) { // Checkbox
            Checkbox(
                checked = book.isRead,
                onCheckedChange = { onToggleRead() },

                )

            // Column for text
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${book.author} - ${book.genre}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Delete button
            IconButton(onClick = onDeleteClick) {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}