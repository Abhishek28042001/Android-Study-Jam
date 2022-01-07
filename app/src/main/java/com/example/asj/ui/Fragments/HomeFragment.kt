package com.example.asj.ui.Fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.asj.Model.Notes
import com.example.asj.R
import com.example.asj.ViewModel.NotesViewModel
import com.example.asj.databinding.FragmentHomeBinding
import com.example.asj.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)


        //get all notes
        viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
//            for(i in notesList){
//                Log.e("!!!!!!!!!", "onCreateView: ${i.subTitle}")
//            }
            val staggeredGridLayoutManager =
                StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
            oldMyNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(), notesList)
            binding.rcvAllNotes.adapter = adapter

        })

        // high filter
        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner, { notesList ->
                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter

            })
        }


        // medium filter
        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner, { notesList ->
                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter

            })
        }


        // low filter
        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner, { notesList ->
                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter

            })
        }


        // all notes filter
        binding.allNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(), notesList)

            })
        }



        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        binding.btnAboutDeveloper.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_developerFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String): Boolean {
                NotesFiltering(p0)

                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(p0: String) {
        Log.e("@@@@", "NotesFiltering: $p0")
        val newFilteredList = arrayListOf<Notes>()
        for (i in oldMyNotes) {
            if (i.title.contains(p0!!) || i.subTitle.contains(p0!!)) {
            }

        }
        adapter.filtering(newFilteredList)
    }
}