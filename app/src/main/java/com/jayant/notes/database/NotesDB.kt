package com.jayant.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jayant.notes.dao.NotesDao
import com.jayant.notes.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDB: RoomDatabase(){

    abstract  fun myNotesDao(): NotesDao

    companion object {
        @Volatile
        var INSTANCE: NotesDB? = null

        fun getDatabaseInstance(context: Context): NotesDB {
            val tempInstance = INSTANCE
            if(tempInstance != null)
                return tempInstance
            synchronized(this) {
                val roomDatabaseInstance =
                    Room.databaseBuilder(context, NotesDB::class.java, "Notes")
                        .allowMainThreadQueries().build()

                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }


}