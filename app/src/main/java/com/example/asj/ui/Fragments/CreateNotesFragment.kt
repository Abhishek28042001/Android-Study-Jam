package com.example.asj.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.asj.Model.Notes
import com.example.asj.R
import com.example.asj.ViewModel.NotesViewModel
import com.example.asj.databinding.FragmentCreateNotesBinding

import java.util.*


class CreateNotesFragment : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding
    var priority: String = "1"
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)
        binding.pRed.setImageResource(R.drawable.ic_done)


        binding.pRed.setOnClickListener {
            priority = "1"
            binding.pRed.setImageResource(R.drawable.ic_done)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.ic_done)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }

        binding.pGreen.setOnClickListener {
            priority = "3"
            binding.pGreen.setImageResource(R.drawable.ic_done)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }


        binding.btnDoneNotes.setOnClickListener {
            createNotes(it)
        }


        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.editTitle.text
        val subTitle = binding.editSubTitle.text
        val notes = binding.editNote.text

        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)

        //Log.e("!!!!!!!!!!!!!", "createNotes:  $notesDate")

        val data = Notes(null,
            title = title.toString(),
            subTitle = subTitle.toString(),
            notes = notes.toString(),
            date = notesDate.toString(),
            priority)

        viewModel.addNotes(data)
        Toast.makeText(requireContext(),"Notes Created Sucessfully",Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)
    }


}