package com.example.asj.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.asj.Model.Notes
import com.example.asj.R
import com.example.asj.ViewModel.NotesViewModel
import com.example.asj.databinding.FragmentEditBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


class EditFragment : Fragment() {

    val oleNotes by navArgs<EditFragmentArgs>()
    lateinit var binding: FragmentEditBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        binding.editTitle.setText(oleNotes.data.title)
        binding.editSubTitle.setText(oleNotes.data.subTitle)
        binding.editNote.setText(oleNotes.data.notes)

        when (oleNotes.data.priority) {
            "1" -> {
                priority = "1"
                binding.pRed.setImageResource(R.drawable.ic_done)
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.pYellow.setImageResource(R.drawable.ic_done)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.pGreen.setImageResource(R.drawable.ic_done)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
        }

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

        binding.btnEditSaveNotes.setOnClickListener {
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.editTitle.text
        val subTitle = binding.editSubTitle.text
        val notes = binding.editNote.text

        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)

        //Log.e("!!!!!!!!!!!!!", "createNotes:  $notesDate")

        val data = Notes(
            oleNotes.data.id,
            title = title.toString(),
            subTitle = subTitle.toString(),
            notes = notes.toString(),
            date = notesDate.toString(),
            priority
        )

        Log.e("######", "updateNotes: Title : $title  subTitle: $subTitle  notes: $notes")

        viewModel.updateNotes(data)
        Toast.makeText(requireContext(), "Notes Update Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editFragment_to_homeFragment)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
//            Log.e("@@@@@", "onOptionsItemSelected: Delete", )

            val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.dialog_delete)
            bottomSheet.show()


            val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)

            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(oleNotes.data.id!!)
                bottomSheet.dismiss()
            }
            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
        }

        return super.onOptionsItemSelected(item)
    }


}