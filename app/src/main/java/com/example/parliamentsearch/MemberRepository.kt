package com.example.parliamentsearch

import androidx.lifecycle.LiveData
import com.example.parliamentsearch.MyApp.Companion.appContext

// Declare an object called MemberRepository
object MemberRepository {
    // Get the DAO from the MemberDatabase instance
    private val dao = MemberDatabase.getInstance(appContext).memberDAO
    // Get all member entries from the DAO and store them in a LiveData object
    val logData: LiveData<List<MemberEntry>> = dao.getAll()

    // Insert new member entries into the database
    fun newMemberEntry(entry: List<MemberEntry>) {
        dao.insert(entry)
    }

    // Get all member entries from a specific political party
    fun getMembersByParty(party: String): LiveData<List<MemberEntry>> {
        return dao.getMembersByParty(party)
    }

    // Get a member entry by its Heteka ID
    fun getMemberById(memberId: Int): LiveData<List<MemberEntry>> {
        return dao.getMemberById(memberId)
    }
}