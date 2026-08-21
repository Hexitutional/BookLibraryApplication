package com.example.booklibraryapplication.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.booklibraryapplication.model.Book

/**
 * ListScreen - Display all books in a scrollable list
 * Follows the pattern from Practical 6
 * - Scaffold with TopAppBar and FLoatingActionButton
 * - LazyColumn with items() and key parameter
 * - AlertDialog for delete confirmation
 * - Empty state handling
 * - State hoisting receives items as parameter
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    items: List<Book>,
    onAddClick: () -> Unit,
    onToggleRead: (Book) -> Unit,
    onDelete: (Book) -> Unit,
    onBookClick: (Book) -> Unit
) {
    // Dialog state
    var bookPendingDelete by remember { mutableStateOf<Book?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My Library") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Filled.Add, contentDescription = "Add Book")
            }
        }
    ) { innerPadding ->
        if (bookPendingDelete != null) {
            AlertDialog(
                onDismissRequest = { bookPendingDelete = null },
                title = { Text("Delete book") },
                text = { Text("Are you sure you want to delete '${bookPendingDelete?.title}'?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            bookPendingDelete?.let { onDelete(it) }
                            bookPendingDelete = null
                        }
                    ) {
                        Text("Delete")
                    }
                }, dismissButton = {
                    TextButton(onClick = { bookPendingDelete = null }) {
                        Text("Cancel")
                    }
                }
            )
        }

        if (items.isEmpty()) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("No books yet. Tap + to add one.")
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = items,
                    key = {it.id}
                ) { book ->
                    BookRow(
                        book = book,
                        onToggleRead = { onToggleRead(book) },
                        onDeleteClick = { bookPendingDelete = book },
                        onBookClick = { onBookClick(book) }
                    )
                }
            }
        }
    }
}





