package com.example.surya.roomappwithnav.Viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.example.surya.roomappwithnav.DatabaseFile.RoomDatabases
import com.example.surya.roomappwithnav.Modal.Note

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val Db: RoomDatabases
    var list: LiveData<MutableList<Note>>
    init{
        Db = Room.databaseBuilder(application, RoomDatabases::class.java,"note.db").allowMainThreadQueries().build()
        list = Db.noteDao().getAll()
    }
    fun fetchAllData() : LiveData<MutableList<Note>>
    {

        list = Db.noteDao().getAll()
        return  list
    }
}