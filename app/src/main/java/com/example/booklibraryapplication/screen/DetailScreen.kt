package com.example.booklibraryapplication.screen

import android.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.booklibraryapplication.model.Book

/**
 * DetailScreen - Shows detailed informaion about a single book
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    book: Book?,
    onBack: () -> Unit
    ) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Book Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxWidth().padding(innerPadding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (book == null) {
                Text("Book not found")
            } else {
                Card(
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val emoji = when (book.genre) {
                            "Fiction" -> "📖"
                            "Non-Fiction" -> "📚"
                            "Sci-Fi" -> "🚀"
                            "Fantasy" -> "🧙"
                            "Classic" -> "📜"
                            "Mystery" -> "🔍"
                            "Romance" -> "❤️"
                            "Horror" -> "👻"
                            else -> "📕"
                        }

                        Text(
                            text = emoji,
                            style = MaterialTheme.typography.displayLarge
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "by ${book.author}",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Genre: ${book.genre}",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = if (book.isRead) "Read" else "Not read",
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (book.isRead)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.error
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = onBack,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text("Back to Library")
                        }
                    }
                }
            }
        }
    }
}