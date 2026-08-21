package com.example.booklibraryapplication.data

import com.example.booklibraryapplication.model.Book

object BookData {
    val sampleBooks = listOf(
        Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Classic", true),
        Book(2, "1984", "George Orwell", "Sci-Fi", false),
        Book(3, "To Kill a Mockingbird", "Harper Lee", "Classic", true),
        Book(4, "The Hobbit", "J.R.R. Tolkien", "Fantasy", false),
        Book(5, "Dune", "Frank Herbert", "Sci-Fi", false),
        Book(6, "Pride and Prejudice", "Jane Austen", "Classic", true),
        Book(7, "The Catcher in the Rye", "J.D. Salinger", "Fiction", false),
        Book(8, "Brave New World", "Aldous Huxley", "Sci-Fi", true),
        Book(9, "The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", false),
        Book(10, "Jane Eyre", "Charlotte Bronte", "Classic", true),
        Book(11, "Animal Farm", "George Orwell", "Fiction", false),
        Book(12, "The Shining", "Stephen King", "Horror", true)
    )

    fun getById(id: Int): Book? = sampleBooks.find { it.id == id}
}