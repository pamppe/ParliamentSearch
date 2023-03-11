package com.example.parliamentsearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class infoFragment : Fragment() {
/**
 * ID: 2201349
 * Emil Lehtonen
 * 6.3.2023
 * last fragment where specific partys member info is shown
 */

    private lateinit var viewModel: PartymemberInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get the member ID from arguments
        val args = infoFragmentArgs.fromBundle(requireArguments())

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_info, container, false)

        // Create an instance of PartymemberInfoViewModel with the member ID as parameter
        viewModel = PartymemberInfoViewModel(args.memberId.split(",").last().toInt())

        // Observe changes in the members LiveData and display the member information in the UI
        viewModel.members.observe(viewLifecycleOwner) {
            // Display the member information in the UI
            view.findViewById<TextView>(R.id.textViewname).text = it.toString().removePrefix("[").removeSuffix("]")
        }
        return view
    }
}

class PartymemberInfoViewModel(val id: Int): ViewModel() {
    // Get the member with the given ID from MemberRepository and transform the data into a list of strings
    var members: LiveData<List<String>> = Transformations.map(MemberRepository.getMemberById(id)){
        it.map { "ID: ${it.hetekaId} \nName: ${it.firstname} ${it.lastname} \nSeat number: ${it.seatNumber} \nParty: ${it.party}"}
    }
}



