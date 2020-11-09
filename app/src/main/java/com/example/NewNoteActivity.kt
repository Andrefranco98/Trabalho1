package com.example

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.dataclass.Note
import com.example.trabalho1.R

class NewNoteActivity : AppCompatActivity() {

    private lateinit var editNoteView: EditText
    private lateinit var editPriorityView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)
        editNoteView = findViewById(R.id.edit_note)
        editPriorityView = findViewById(R.id.edit_priority)


        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNoteView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val note = editNoteView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY_NOTE, note)
                replyIntent.putExtra(EXTRA_REPLY_PRIORITY, note)
                setResult(Activity.RESULT_OK, replyIntent)


            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY_NOTE = "com.example.trabalho3.REPLY_NOTE"
        const val EXTRA_REPLY_PRIORITY = "com.example.trabalho3.REPLY_PRIORITY"
    }


}
