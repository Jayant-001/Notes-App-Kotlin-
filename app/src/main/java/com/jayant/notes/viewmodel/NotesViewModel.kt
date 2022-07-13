package com.jayant.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.jayant.notes.database.NotesDB
import com.jayant.notes.model.Note
import com.jayant.notes.repository.NotesRepository

class NotesViewModel(application: Application): AndroidViewModel(application) {

    val repository: NotesRepository

    init {
        val dao = NotesDB.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    fun addNote(note: Note) {
        repository.insertNote(note)
    }

    fun getNotes(): LiveData<List<Note>> = repository.getAllNotes()
    fun getHighNotes(): LiveData<List<Note>> = repository.getHighNotes()
    fun getMediumNotes(): LiveData<List<Note>> = repository.getMediumNotes()
    fun getLowNotes(): LiveData<List<Note>> = repository.getLowNotes()

    fun deleteNote(id: Int) {
        repository.deleteNote(id)
    }

    fun updateNote(note: Note) {
        repository.update(note)
    }
}