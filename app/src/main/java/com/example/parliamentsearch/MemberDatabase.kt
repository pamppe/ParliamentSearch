package com.example.parliamentsearch

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
/**
 * An abstract class that represents the Member database.
 *@property memberDAO the data access object for Member entries
 * Returns a singleton instance of the MemberDatabase.
 * @param context the context of the application
 * @return the MemberDatabase instance
 */
@Database(entities = [MemberEntry::class], version = 5, exportSchema = false)
abstract class MemberDatabase: RoomDatabase() {
    abstract val memberDAO: MemberDAO
    companion object {
        @Volatile
        private var INSTANCE: MemberDatabase? = null
        fun getInstance(context: Context): MemberDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(MyApp.appContext, MemberDatabase::class.java, "database")
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}