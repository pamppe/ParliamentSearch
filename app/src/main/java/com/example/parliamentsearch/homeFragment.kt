package com.example.parliamentsearch

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.example.parliamentsearch.databinding.FragmentHomeBinding


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class homeFragment : Fragment() {
    /**
    * ID: 2201349
    * Emil Lehtonen
    * 1.3.2023
    * First fragment with button
    */

    // Declare a private lateinit variable for the ViewModel
    private lateinit var viewModel: homefragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Log a debug message to indicate that this code is working
        Log.d("Toimii?", "Works")
        // Inflate the layout for this fragment using the data binding object
        val binding = FragmentHomeBinding.inflate(layoutInflater)

        // Set an OnClickListener for the search button
        binding.searchbutton.setOnClickListener {
            // Navigate to the member fragment
            findNavController().navigate(R.id.action_homeFragment_to_memberFragment)
            // Launch a coroutine to read the member data using the ViewModel
            lifecycleScope.launch{
                viewModel.readMembers()
            }
        }
        // Instantiate the ViewModel using the ViewModelProvider
        viewModel = ViewModelProvider(this).get(homefragmentViewModel::class.java)
        // Return the root view for this fragment
        return binding.root
    }
}
class homefragmentViewModel: ViewModel() {

    // A suspend function to read the members from the network
    suspend fun readMembers() {
        // Execute this function on the IO dispatcher
        withContext(Dispatchers.IO) {
            try {
                // Call the getMemberList function from the ParliamentApiService using Retrofit
                val members = MemberApi.retrofitService.getMemberList()
                // Save the members to the MemberRepository
                MemberRepository.newMemberEntry(members)
                // Log a debug message to indicate that this code is working
                Log.d("Toimii?", "Works")
                // Log the number of members in the MemberRepository
                Log.d("Toimii?", "${MemberRepository.logData.value?.size}")
                // Print a message indicating that the members were read successfully
                println("Read members from NW with great success.")
            } catch (e: Exception) {
                // Print a message indicating that there was an error reading the members
                println("No luck in reading members from NW: $e")
            }
        }
    }
}