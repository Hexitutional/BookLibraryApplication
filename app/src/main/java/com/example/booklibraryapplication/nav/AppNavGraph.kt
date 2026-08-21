package com.example.booklibraryapplication.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.booklibraryapplication.data.BookData
import com.example.booklibraryapplication.model.Book
import com.example.booklibraryapplication.screen.AddScreen
import com.example.booklibraryapplication.screen.DetailScreen
import com.example.booklibraryapplication.screen.ListScreen


/**
 * AppNavGraph - Sets up the navigation graph
 *
 * Follows the pattern from Practical 4 and Practical 5:
 * - Uses NavHost with rememberNavController()
 * - Defines composable destinations with routes
 * - Passes arguments between screens using navArgument
 * - Implements state hoisting with mutableStateListOf (from Practical 6)
 */

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    // List that survives after changing screens, the app will remember what changes have been made
    // and the data used is from the BookData.kt file
    val items = remember {
        mutableStateListOf<Book>().apply { addAll(BookData.sampleBooks) }
    }

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ListScreen(
                items = items,
                onAddClick = { navController.navigate("add") },
                onToggleRead = { book ->
                    val index = items.indexOfFirst { it.id == book.id }
                    if (index != -1) {
                        items[index] = items[index].copy(isRead = !items[index].isRead)
                    }
                },
                onDelete = { book ->
                    items.removeAll{ it.id == book.id }
                },
                onBookClick =  { book ->
                    navController.navigate("detail/${book.id}")
                }
            )
        }

        composable("add") {
            AddScreen(
                onSave = { title, author, genre, isRead ->
                    val nextId = (items.maxOfOrNull { it.id } ?: 0) + 1
                    items.add(
                        Book(
                            id = nextId,
                            title = title,
                            author = author,
                            genre = genre,
                            isRead = isRead
                        )
                    )

                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = "detail/{bookId}", arguments = listOf(navArgument("bookId") {type =
            NavType.IntType})) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: 0
            val book = items.find { it.id == bookId }
            DetailScreen(
                book = book,
                onBack = { navController.popBackStack() }
            )
        }
    }
}