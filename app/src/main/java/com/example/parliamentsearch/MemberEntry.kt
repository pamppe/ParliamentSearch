package com.example.parliamentsearch
import androidx.lifecycle.LiveData
import androidx.room.*
import java.lang.reflect.Member

@Entity
data class MemberEntry(
    @PrimaryKey(autoGenerate = false)
    val hetekaId: Int,
    val seatNumber: Int,
    val lastname: String,
    val firstname: String,
    val party: String,
    val minister: Boolean = false,
    val pictureUrl: String = ""
)

@Dao
interface MemberDAO {

    //The insert() method is used to insert one or more MemberEntry objects into the table. The onConflict parameter is used to specify the conflict resolution strategy when a duplicate entry is inserted.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entry: List<MemberEntry>)

    //The getAll() method retrieves all the MemberEntry objects from the Member table and returns them as a LiveData object.
    @Query("select * from MemberEntry")
    fun getAll(): LiveData<List<MemberEntry>>

    //The getAllParties() method retrieves all the distinct parties from the Member table and returns them as a LiveData object.
    @Query("SELECT DISTINCT party FROM MemberEntry")
    fun getAllParties(): LiveData<List<String>>

    //The getMembersByParty(party: String) method retrieves all the MemberEntry objects belonging to a specific party from the Member table and returns them as a LiveData object.
    @Query("SELECT * FROM MemberEntry WHERE party = :party")
    fun getMembersByParty(party: String): LiveData<List<MemberEntry>>

    //The getMemberById(id: Int) method retrieves a specific MemberEntry object from the Member table based on its hetekaId and returns it as a LiveData object.
    @Query("SELECT * FROM MemberEntry WHERE hetekaId = :id")
    fun getMemberById(id: Int): LiveData<List<MemberEntry>>
}
