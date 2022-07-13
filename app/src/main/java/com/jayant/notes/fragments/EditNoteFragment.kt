package com.jayant.notes.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jayant.notes.R
import com.jayant.notes.databinding.FragmentEditNoteBinding
import com.jayant.notes.model.Note
import com.jayant.notes.viewmodel.NotesViewModel
import java.util.*

class EditNoteFragment : Fragment() {

    val oldNote by navArgs<EditNoteFragmentArgs>()
    lateinit var binding: FragmentEditNoteBinding
    private var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        binding.editNoteTitle.setText(oldNote.noteData.title)
        binding.editNoteSubtitle.setText(oldNote.noteData.subtitle)
        binding.editNoteDescription.setText(oldNote.noteData.description)

        // getting priority from NotesAdapter and show priority color on color bar
        when (oldNote.noteData.priority) {
            "1" -> {
                binding.editNotePriorityWhite.setImageResource(R.drawable.ic_done)
                binding.editNotePriorityRed.setImageResource(0)
                binding.editNotePriorityGreen.setImageResource(0)
                priority = "1"
            }
            "2" -> {
                binding.editNotePriorityGreen.setImageResource(R.drawable.ic_done)
                binding.editNotePriorityWhite.setImageResource(0)
                binding.editNotePriorityRed.setImageResource(0)
                priority = "2"
            }
            "3" -> {
                binding.editNotePriorityRed.setImageResource(R.drawable.ic_done)
                binding.editNotePriorityGreen.setImageResource(0)
                binding.editNotePriorityWhite.setImageResource(0)
                priority = "2"
            }
        }

        // update priority of the existing note
        binding.editNotePriorityWhite.setOnClickListener {
            binding.editNotePriorityWhite.setImageResource(R.drawable.ic_done)
            binding.editNotePriorityRed.setImageResource(0)
            binding.editNotePriorityGreen.setImageResource(0)
            priority = "1"
        }

        binding.editNotePriorityGreen.setOnClickListener {
            binding.editNotePriorityGreen.setImageResource(R.drawable.ic_done)
            binding.editNotePriorityRed.setImageResource(0)
            binding.editNotePriorityWhite.setImageResource(0)
            priority = "2"
        }

        binding.editNotePriorityRed.setOnClickListener {
            binding.editNotePriorityRed.setImageResource(R.drawable.ic_done)
            binding.editNotePriorityGreen.setImageResource(0)
            binding.editNotePriorityWhite.setImageResource(0)
            priority = "3"
        }

        binding.editNoteSaveBtn.setOnClickListener {

            updateNote(it)
        }


        return binding.root
    }

    private fun updateNote(it: View) {

        val title = binding.editNoteTitle.text.toString()
        val subtitle = binding.editNoteSubtitle.text.toString()
        val desc = binding.editNoteDescription.text.toString()

        val d = Date()
        val date: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        // geting current id from old note to update existing note
        val note = Note(oldNote.noteData.id, title, subtitle, desc, priority, date.toString())
        viewModel.updateNote(note)

        Toast.makeText(it.context, "Note Updated Successfully.", Toast.LENGTH_SHORT).show()

//        Navigation.findNavController(it).popBackStack(R.id.action_editNoteFragment_to_homeFragment, true)
        Navigation.findNavController(it).navigate(R.id.action_editNoteFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.btn_delete) {

            // show bottom delete option sheet
            val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext(), R.style.bottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)
            bottomSheet.show()

            val textViewYes = bottomSheet.findViewById<TextView>(R.id.btn_delete_yes)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.btn_delete_no)

            textViewNo?.setOnClickListener {

                bottomSheet.dismiss()
            }
            textViewYes?.setOnClickListener {
                viewModel.deleteNote(oldNote.noteData.id!!)
                Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show()
//                Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)
                bottomSheet.dismiss()
//                finish current fragment
                findNavController().popBackStack()
            }

        }
        return super.onOptionsItemSelected(item)
    }

}