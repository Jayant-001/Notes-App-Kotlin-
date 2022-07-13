package com.jayant.notes.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jayant.notes.model.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes ORDER BY id DESC")
    fun getNotes() : LiveData<List<Note>>

    @Query("SELECT * FROM Notes WHERE priority=3")
    fun getHighNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM Notes WHERE priority=2")
    fun getMediumNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM Notes WHERE priority=1")
    fun getLowNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNote(id: Int)

    @Update
    fun updateNode(note: Note)
}