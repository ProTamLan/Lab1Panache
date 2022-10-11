package com.example.panache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.panache.Flashcard
import com.example.panache.FlashcardDao

@Database(entities = [Flashcard::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun flashcardDao(): FlashcardDao
}
