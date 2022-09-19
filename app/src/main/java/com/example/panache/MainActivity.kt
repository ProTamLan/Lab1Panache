package com.example.panache

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val answer1 = findViewById<TextView>(R.id.a1)
        val answer2 = findViewById<TextView>(R.id.a2)
        val answer3 = findViewById<TextView>(R.id.a3)
        val question = findViewById<TextView>(R.id.flashcard_question)
        val correctanswer = findViewById<TextView>(R.id.flashcard_answer)
        val mode = findViewById<TextView>(R.id.mode)
        /*val background = findViewById<TextView>(R.id.background)
        background.setOnClickListener{
            answer1.visibility = View.VISIBLE
            answer2.visibility = View.VISIBLE
            answer3.visibility = View.VISIBLE
            question.visibility = View.INVISIBLE
            correctanswer.visibility = View.VISIBLE
        }*/
        //startActivity(MyActivity.class, getIntent())
        question.setOnClickListener {
            question.visibility = View.INVISIBLE
            correctanswer.visibility = View.VISIBLE
        }
        correctanswer.setOnClickListener {
            question.visibility = View.VISIBLE
            correctanswer.visibility = View.INVISIBLE
        }

        answer1.setOnClickListener {
            findViewById<View>(R.id.a1).setBackgroundColor(getResources().getColor(R.color.red, null))
            findViewById<View>(R.id.a3).setBackgroundColor(getResources().getColor(R.color.green, null))
        }
        answer2.setOnClickListener {
            answer2.visibility = View.VISIBLE
            correctanswer.visibility = View.INVISIBLE
            findViewById<View>(R.id.a2).setBackgroundColor(getResources().getColor(R.color.red, null))
            findViewById<View>(R.id.a3).setBackgroundColor(getResources().getColor(R.color.green, null))
        }
        answer3.setOnClickListener {
            findViewById<View>(R.id.a3).setBackgroundColor(getResources().getColor(R.color.green, null))
        }
        mode.setOnClickListener {
            answer1.visibility = View.INVISIBLE
            answer2.visibility = View.INVISIBLE
            answer3.visibility = View.INVISIBLE
        }

    }
}