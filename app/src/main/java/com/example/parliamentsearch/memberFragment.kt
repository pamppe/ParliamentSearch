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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MemberFragment : Fragment() {
    /**
     * ID: 2201349
     * Emil Lehtonen
     * 4.3.2023
     * Second fragment where every party is seen in a recyclerview
     */

    private lateinit var MyListAdapter: MyListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var memberViewModel: MemberViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_member, container, false)
        recyclerView = view.findViewById(R.id.myRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        MyListAdapter = MyListAdapter(emptyList()) // create an adapter with an empty list
        recyclerView.adapter = MyListAdapter

        // initialize the ViewModel and observe the changes in the database
        memberViewModel = ViewModelProvider(this).get(MemberViewModel::class.java)
        memberViewModel.itemList.observe(viewLifecycleOwner) {
        itemList -> MyListAdapter.itemList = itemList
            MyListAdapter.notifyDataSetChanged()
        }

        return view
    }
}
class MemberViewModel: ViewModel() {

    val itemList: LiveData<List<String>> = Transformations.map(MemberRepository.logData) {
        it.map { it.party }.toSortedSet().toList()
    }
}
