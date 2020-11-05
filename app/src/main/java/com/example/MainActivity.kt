package com.example

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ViewModel.NoteViewModel
import com.example.adapter.NoteListAdapter
import com.example.dataclass.Note
import com.example.trabalho1.R
import com.google.android.material.floatingactionbutton.FloatingActionButton



class MainActivity : AppCompatActivity() {

    private val newNoteActivityRequestCode = 1
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NoteListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.allNotes.observe(this, Observer { notes ->
            // Update the cached copy of the words in the adapter.
            notes?.let { adapter.setNotes(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
            startActivityForResult(intent, newNoteActivityRequestCode)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    //Menu de opções
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.opt1->{
                val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
                startActivityForResult(intent,newNoteActivityRequestCode)
                true
            }
            R.id.opt2->{
                Toast.makeText(this, "teste2", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.opt3->{
                Toast.makeText(this, "teste3", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newNoteActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val pnote = data?.getStringExtra(NewNoteActivity.EXTRA_REPLY_NOTE)
            val ppriority = data?.getStringExtra(NewNoteActivity.EXTRA_REPLY_PRIORITY)

            if (pnote!= null && ppriority != null) {
                val note = Note(note = pnote, priority = ppriority)
                noteViewModel.insert(note)
            }

        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}