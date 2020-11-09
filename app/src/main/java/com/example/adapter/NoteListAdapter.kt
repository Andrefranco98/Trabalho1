package com.example.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.dataclass.Note
import com.example.trabalho1.R


class NoteListAdapter internal constructor(
    context: Context,
    ) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {
    private var notes: List<Note> = ArrayList()
    private val inflater: LayoutInflater = LayoutInflater.from(context)




    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = notes[position]
        holder.noteItemView.text = current.note

    }

    internal fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }





    override fun getItemCount() = notes.size

    fun getNoteAt(position: Int): Note? {
        return notes[position]
    }

}