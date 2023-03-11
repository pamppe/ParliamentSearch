package com.example.parliamentsearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class MyListAdapter(var itemList: List<String>) : RecyclerView.Adapter<MyListAdapter.ViewHolder>() {
    /**
     * ID: 2201349
     * Emil Lehtonen
     * 4.3.2023
     * Adapter for memberFragment
     */

    // Define a ViewHolder to hold a list item view
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

        // Create a new ViewHolder and inflate its view
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            return ViewHolder(itemView)
        }
        // Return the number of items in the list
        override fun getItemCount(): Int {
            return itemList.size
        }
        // Bind the data for a particular position to a ViewHolder
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            // Set the text of the TextView to the string at the current position
            holder.itemView.findViewById<TextView>(R.id.itemParty).apply {
                text = itemList[position]
                // Set an onClickListener for the TextView
                setOnClickListener {
                    // Get the party name as a string and create a navigation action to the PartymemberFragment
                    val party = text.toString()
                    val action = MemberFragmentDirections.actionMemberFragmentToPartymemberFragment(party)
                    // Navigate to the PartymemberFragment
                    it.findNavController().navigate(action)
                }
            }
        }
    }
