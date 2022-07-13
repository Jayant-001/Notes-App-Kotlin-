package com.jayant.notes.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.jayant.notes.R
import com.jayant.notes.databinding.FragmentCreateNoteBinding
import com.jayant.notes.model.Note
import com.jayant.notes.viewmodel.NotesViewModel
import java.util.*

class CreateNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateNoteBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)

        binding.createNoteSaveBtn.setOnClickListener {
            createNote(it)
        }

        binding.createNotePriorityWhite.setImageResource(R.drawable.ic_done)
        binding.createNotePriorityWhite.setOnClickListener {
            binding.createNotePriorityWhite.setImageResource(R.drawable.ic_done)
            binding.createNotePriorityRed.setImageResource(0)
            binding.createNotePriorityGreen.setImageResource(0)
            priority = "1"
        }

        binding.createNotePriorityGreen.setOnClickListener {
            binding.createNotePriorityGreen.setImageResource(R.drawable.ic_done)
            binding.createNotePriorityRed.setImageResource(0)
            binding.createNotePriorityWhite.setImageResource(0)
            priority = "2"
        }

        binding.createNotePriorityRed.setOnClickListener {
            binding.createNotePriorityRed.setImageResource(R.drawable.ic_done)
            binding.createNotePriorityGreen.setImageResource(0)
            binding.createNotePriorityWhite.setImageResource(0)
            priority = "3"
        }

        return binding.root
    }

    private fun createNote(it: View) {

        val title = binding.createNoteTitle.text.toString()
        val subtitle = binding.createNoteSubtitle.text.toString()
        val desc = binding.createNoteDescription.text.toString()

        val d = Date()
        val date : CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)
//        Log.d("jayant" , "$date")

        val note = Note(null, title, subtitle, desc, priority, date.toString())
        viewModel.addNote(note)

        Toast.makeText(it.context, "Note created successfully.", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it).navigate(R.id.action_createNoteFragment_to_homeFragment)

        Navigation.findNavController(it).navigateUp()
    }
}