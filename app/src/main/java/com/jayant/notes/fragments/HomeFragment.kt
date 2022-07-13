package com.jayant.notes.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jayant.notes.R
import com.jayant.notes.adapter.NotesAdapter
import com.jayant.notes.databinding.FragmentHomeBinding
import com.jayant.notes.model.Note
import com.jayant.notes.viewmodel.NotesViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var myAllNotes = arrayListOf<Note>()
    private lateinit var adapter: NotesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        binding.fabCreateNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

//        binding.allNotesRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->

            if(notesList.isNotEmpty()) binding.noNoteMessage.visibility = View.GONE
            myAllNotes = notesList as ArrayList<Note> /* = java.util.ArrayList<com.jayant.notes.model.Note> */
            binding.allNotesRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = NotesAdapter(requireContext(), notesList)
            binding.allNotesRv.adapter = adapter

        }

        binding.btnFilter.setOnClickListener {

            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->

                myAllNotes = notesList as ArrayList<Note>
                binding.allNotesRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(), notesList)
                binding.allNotesRv.adapter = adapter

            }
        }

        binding.filterHigh.setOnClickListener {

            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->

                myAllNotes = notesList as ArrayList<Note>
                binding.allNotesRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(), notesList)
                binding.allNotesRv.adapter = adapter

            }
        }
        binding.filterMedium.setOnClickListener {

            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->

                myAllNotes = notesList as ArrayList<Note>
                binding.allNotesRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(), notesList)
                binding.allNotesRv.adapter = adapter

            }
        }
        binding.filterLow.setOnClickListener {

            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->

                myAllNotes = notesList as ArrayList<Note>
                binding.allNotesRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(), notesList)
                binding.allNotesRv.adapter = adapter

            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Note Title..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                noteFiltering(p0)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun noteFiltering(p0: String?) {

        val newFilteredList = arrayListOf<Note>()

        for(i in myAllNotes) {
            val title = i.title.lowercase()
            val subtitle = i.subtitle.lowercase()
            if(title.contains(p0!!.lowercase()) || subtitle.contains(p0!!.lowercase()))
                newFilteredList.add(i)
        }

        adapter.filter(newFilteredList)
    }


}