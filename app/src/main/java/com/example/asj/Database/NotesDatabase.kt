package com.example.asj.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.asj.Dao.NotesDao
import com.example.asj.Model.Notes
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun myNotesDao(): NotesDao

    companion object{
        var INSTANCE:NotesDatabase?=null


        @OptIn(InternalCoroutinesApi::class)
        fun getDatabaseInstance(context: Context):NotesDatabase{
            var tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                var roomDatabaseInstance = Room.databaseBuilder(context,NotesDatabase::class.java,"Notes").allowMainThreadQueries().build()
                INSTANCE=roomDatabaseInstance
                return return roomDatabaseInstance
            }
        }
    }
}