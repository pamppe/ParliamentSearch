package com.example.parliamentsearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class partymemberFragment : Fragment() {
    /**
     * ID: 2201349
     * Emil Lehtonen
     * 5.3.2023
     * Fragment for showing single partys all members
     */

    private lateinit var MyListAdapterparty: MyListAdapterparty
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: PartymemberViewModel
    private lateinit var viewModelFactory: PartymemberViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // get the party argument from the navigation and create the view model factory
        val args = partymemberFragmentArgs.fromBundle(requireArguments())
        viewModelFactory = PartymemberViewModelFactory(args.party)

        // Inflate the layout for this fragment and set up the RecyclerView
        val view = inflater.inflate(R.layout.fragment_partymember, container, false)
        recyclerView = view.findViewById(R.id.myRecyclerViewmember)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // create the adapter and set it to the RecyclerView
        MyListAdapterparty = MyListAdapterparty(emptyList())
        recyclerView.adapter = MyListAdapterparty

        // initialize the ViewModel and observe the changes in the database
        viewModel = ViewModelProvider(this, viewModelFactory)[PartymemberViewModel::class.java]
        viewModel.memberList.observe(viewLifecycleOwner) { memberList ->
            MyListAdapterparty.memberList = memberList
            MyListAdapterparty.notifyDataSetChanged()
        }

        // Add click listener to each item in the RecyclerView
        MyListAdapterparty.setOnItemClickListener { member ->
            val action = partymemberFragmentDirections.actionPartymemberFragmentToInfoFragment(member.name)
            findNavController().navigate(action)
        }

        return view
    }
}

class PartymemberViewModel(party: String): ViewModel() {
    // observe the member list from the database and map the data to a string list
    val memberList: LiveData<List<String>> = Transformations.map(MemberRepository.getMembersByParty(party)) {
        it.map { "${it.firstname} ${it.lastname},${it.hetekaId}" }.distinct()
    }
}

class PartymemberViewModelFactory(private val party: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PartymemberViewModel::class.java)) {
            return PartymemberViewModel(party) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}