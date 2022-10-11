package com.example.panache

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddCardActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_add_card)

            val questionEditText = findViewById<EditText>(R.id.flashcard_question_edittext)
            val answerEditText = findViewById<EditText>(R.id.flashcard_answer_edittext) //enter text in this id text box

        val saveButton = findViewById<ImageView>(R.id.flashcard_save_button)
        saveButton.setOnClickListener {//new save card
            val questionString = questionEditText.text.toString()
            val answerString = answerEditText.text.toString()
            if (answerString.isEmpty() || questionString.isEmpty() ){ //if ans or ques = empty show toast else...
                Toast.makeText(this, "Must Enter Both Question & Answer", Toast.LENGTH_SHORT).show() //shows Toast for empty one or two...
            }
            else{
                val data = Intent()                              // create a new Intent, this is where we will put our data
                data.putExtra("QUESTION_KEY", questionString)   // puts one string into the Intent, with the key as 'string1'
                data.putExtra("ANSWER_KEY", answerString)     // puts another string into the Intent, with the key as 'string2'
                setResult(RESULT_OK, data)                      // set result code and bundle data for response
                finish()                                        // closes this activity and pass data to the original activity that launched this activity
            }
        }
            //Toast.makeText(this, "Must Enter Both Question & Answer", Toast.LENGTH_SHORT).show()
        val cancelButton = findViewById<ImageView>(R.id.flashcard_cancel_button) //when u press cancel adding a new flashcard
        cancelButton.setOnClickListener { // it will finish() it.
        finish()
        }
        }
 }