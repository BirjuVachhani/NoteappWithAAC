package com.example.surya.roomappwithnav.Adapters

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.surya.roomappwithnav.Modal.Note
import com.example.surya.roomappwithnav.R
import kotlinx.android.synthetic.main.note_card.view.*

class RoomAdap(context: Context, private var items: List<Note>,view: View): RecyclerView.Adapter<RoomAdap.ViewHolder>()
{
    var context: Context
    var view: View

    init {
        this.context = context
        this.view = view
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notedesc: String = items.get(position).note
        holder.notedescription?.text = notedesc

        holder.notedescription.setOnClickListener {
            var mArgs =Bundle()
            mArgs.putInt("Edit", 1)
            mArgs.putString("Note", notedesc)
            mArgs.putString("key", items.get(position).id.toString())
            Navigation.findNavController(view).navigate(R.id.createnote_fragment,mArgs)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val v =  LayoutInflater.from(parent.context).inflate(R.layout.note_card,parent,false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        var notedescription = view.notedesc
    }
}