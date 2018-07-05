package com.example.surya.roomappwithnav.DaoFile

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.example.surya.roomappwithnav.Modal.Note


@Dao
interface NoteDao {

    @Query("SELECT * from notetable ORDER BY id DESC")
    fun getAll(): LiveData<MutableList<Note>>

    @Insert
    fun insert(user: Note)

    @Update
    fun update(user: Note)

    @Query("DELETE from notetable")
    fun deleteAll()

}