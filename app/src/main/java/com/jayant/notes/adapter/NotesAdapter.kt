package com.jayant.notes.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.jayant.notes.R
import com.jayant.notes.databinding.FragmentCreateNoteBinding
import com.jayant.notes.databinding.ItemNoteBinding
import com.jayant.notes.fragments.HomeFragmentDirections
import com.jayant.notes.model.Note

class NotesAdapter(val context: Context, private var notesList: List<Note>): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        return NotesViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        holder.binding.itemNoteTitle.text = notesList[position].title
        holder.binding.itemNoteSubtitle.text = notesList[position].subtitle
        holder.binding.itemNoteDate.text = notesList[position].date

//        Log.d("jayant", notesList[position].priority)

        when (notesList[position].priority)
        {
            "1" -> {
                holder.binding.itemNoteLayout.setCardBackgroundColor(context.getColorStateList(R.color.blue))
                holder.binding.itemNotePriority.setBackgroundResource(R.drawable.dot_white)
            }
            "2" -> {
                holder.binding.itemNoteLayout.setCardBackgroundColor(context.getColorStateList(R.color.green))
                holder.binding.itemNotePriority.setBackgroundResource(R.drawable.dot_green)
            }
            "3" -> {
                holder.binding.itemNoteLayout.setCardBackgroundColor(context.getColorStateList(R.color.orange))
                holder.binding.itemNotePriority.setBackgroundResource(R.drawable.dot_orange)
            }
        }

        holder.binding.root.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(notesList[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun filter(newFilteredList: ArrayList<Note>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }

    class NotesViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}