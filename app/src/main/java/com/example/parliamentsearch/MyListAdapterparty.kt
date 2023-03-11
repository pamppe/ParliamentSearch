package com.example.parliamentsearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Member

class MyListAdapterparty(var memberList: List<String>) : RecyclerView.Adapter<MyListAdapterparty.ViewHolder>() {
    /**
     * ID: 2201349
     * Emil Lehtonen
     * 5.3.2023
     * Adapter for partymemberFragment
     */
    // Listener to handle item click events
    private var listener: ((Member) -> Unit)? = null

    // Sets the click listener for items
    fun setOnItemClickListener(listener: (Member) -> Unit) {
        this.listener = listener
    }

    // ViewHolder class for each item in the list
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    // Inflates the layout for each item and returns a ViewHolder instance
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_itemparty, parent, false)
        return ViewHolder(itemView)
    }

    // Returns the size of the list
    override fun getItemCount(): Int {
        return memberList.size
    }

    // Binds data to the ViewHolder instance at the specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.itemMember).apply {
            text = memberList[position]
            holder.itemView.setOnClickListener {
                // Navigate to the InfoFragment passing the name of the selected member as an argument
                val action = partymemberFragmentDirections.actionPartymemberFragmentToInfoFragment(text.toString())
                Navigation.findNavController(holder.itemView).navigate(action)
            }
        }
    }
    }

