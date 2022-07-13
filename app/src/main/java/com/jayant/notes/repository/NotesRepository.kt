package com.jayant.notes.repository

import androidx.lifecycle.LiveData
import com.jayant.notes.dao.NotesDao
import com.jayant.notes.model.Note

class NotesRepository(private val dao: NotesDao) {

    fun getAllNotes() : LiveData<List<Note>> {
        return dao.getNotes()
    }

    fun getHighNotes() : LiveData<List<Note>> {
        return dao.getHighNotes()
    }

    fun getMediumNotes() : LiveData<List<Note>> {
        return dao.getMediumNotes()
    }

    fun getLowNotes() : LiveData<List<Note>> {
        return dao.getLowNotes()
    }

    fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    fun deleteNote(id: Int) {
        dao.deleteNote(id)
    }

    fun update(note: Note) {
        dao.updateNode(note)
    }

}