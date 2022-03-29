package com.example.vananimalcare

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels


class FragmentMap : DialogFragment() {

    private val viewModel : FragmentViewModel by activityViewModels()
    private val viewModelB : FragmentViewModelB by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_map, container, false)

        viewModel.data.observe(viewLifecycleOwner){
            rootView.findViewById<TextView>(R.id.shelterTitle).text = it
        }

        viewModelB.data.observe(viewLifecycleOwner){
            rootView.findViewById<TextView>(R.id.shelterDesc).text = it
        }

        rootView.findViewById<Button>(R.id.button).setOnClickListener {
            dismiss()
            //plan to put pathfinding stuff here
        }

        return rootView

    }

    companion object {

        fun newInstance() : FragmentMap {
            return FragmentMap()
        }
    }
}

//ARCHIVED BACKUP CODE

//rootView.findViewById<TextView>(R.id.shelterTitle).text = intent.getString("title")

//displayMessage = arguments?.getString("message")
//rootView.findViewById<TextView>(R.id.shelterTitle).text = displayMessage