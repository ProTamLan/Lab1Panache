package com.example.panache

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.yourpackage.packagenamehere.Flashcard
import com.yourpackage.packagenamehere.FlashcardDatabase

class MainActivity : AppCompatActivity() {

    var currCardDisplayedIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val answer1 = findViewById<TextView>(R.id.a1)
        val answer2 = findViewById<TextView>(R.id.a2)
        val answer3 = findViewById<TextView>(R.id.a3)
        val question = findViewById<TextView>(R.id.flashcard_question)
        val correctanswer = findViewById<TextView>(R.id.flashcard_answer)
        val mode = findViewById<TextView>(R.id.mode)
        var shouldShowAnswers = true
        /*val background = findViewById<TextView>(R.id.background)
        background.setOnClickListener{
            answer1.visibility = View.VISIBLE
            answer2.visibility = View.VISIBLE
            answer3.visibility = View.VISIBLE   IGNORE
            question.visibility = View.INVISIBLE
            correctanswer.visibility = View.VISIBLE
        }*/
        //startActivity(MyActivity.class, getIntent())
        question.setOnClickListener {
            question.visibility = View.INVISIBLE
            correctanswer.visibility = View.VISIBLE
            Snackbar.make(question, "Flashcard button was flipped",
                Snackbar.LENGTH_SHORT).show()
            Log.i("Alan", "Flashcard button was flipped")
        }
        correctanswer.setOnClickListener {
            question.visibility = View.VISIBLE
            correctanswer.visibility = View.INVISIBLE //Issue makes flashcard invisible
            Toast.makeText(this, "Flashcard button was flipped", Toast.LENGTH_SHORT).show()
        }
        answer1.setOnClickListener {
            findViewById<View>(R.id.a1).setBackgroundColor(getResources().getColor(R.color.red, null))
            findViewById<View>(R.id.a3).setBackgroundColor(getResources().getColor(R.color.green, null))
            Toast.makeText(this, "Answer1 button was clicked", Toast.LENGTH_SHORT).show()
        }
        answer2.setOnClickListener {
            answer2.visibility = View.VISIBLE
            correctanswer.visibility = View.INVISIBLE
            findViewById<View>(R.id.a2).setBackgroundColor(getResources().getColor(R.color.red, null))
            findViewById<View>(R.id.a3).setBackgroundColor(getResources().getColor(R.color.green, null))
            Toast.makeText(this, "Answer2 button was clicked", Toast.LENGTH_SHORT).show()
        }
        answer3.setOnClickListener {
            findViewById<View>(R.id.a3).setBackgroundColor(getResources().getColor(R.color.green, null))

            Toast.makeText(this, "Answer3 button was clicked", Toast.LENGTH_SHORT).show()
        }
        mode.setOnClickListener { //implementing the onclicklistener
            if (shouldShowAnswers){
                answer1.visibility = View.INVISIBLE
                answer2.visibility = View.INVISIBLE
                answer3.visibility = View.INVISIBLE
                shouldShowAnswers = false
                Toast.makeText(this, "Exam button was clicked (1)", Toast.LENGTH_SHORT).show()
            }
            else{
                answer1.visibility = View.VISIBLE
                answer2.visibility = View.VISIBLE
                answer3.visibility = View.VISIBLE
                shouldShowAnswers = true
                Toast.makeText(this, "Exam button was clicked (2)", Toast.LENGTH_SHORT).show()
            }
        }

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result ->
            val data: Intent? = result.data
            if (data != null) { // Check that we have data returned
                val questionString = data.getStringExtra("QUESTION_KEY") // needs to match the key we used when we put the string in the Intent
                val answerString = data.getStringExtra("ANSWER_KEY")

                Snackbar.make(findViewById(R.id.add_question_button), "Card Created...", Snackbar.LENGTH_SHORT).show() // card created notification

                val flashcard = Flashcard(questionString!!, answerString!!, wrongAnswer1 = null, wrongAnswer2 = null) //insures its not null
                val flashcardDatabase = FlashcardDatabase(this)
                flashcardDatabase.insertCard(flashcard)
                question.text = questionString
                correctanswer.text = answerString //ans3 = correct green ans

                Log.i("Alan: MainActivity", "question: $questionString") //main flashcard page
                Log.i("Alan: MainActivity", "answer: $answerString")
            }
            else {
                Log.i("Alan: MainActivity", "Returned null data from AddCardActivity")
            }
        }

        val addQuestionButton=findViewById<ImageView>(R.id.add_question_button)
        addQuestionButton.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            // Launch EndingActivity with the resultLauncher so we can execute more code - once we come back here from EndingActivity
            resultLauncher.launch(intent)
        }
        /*val nextButton = findViewById<ImageView>(R.id.flashcard_next_card_button)
        nextButton.setOnClickListener{
            if (allFlashcards.isEmpty()){
                return@setOnClickListener
            }
            currCardDisplayedIndex++
            if(currCardDisplayedIndex >= allFlashcards.size){
                //start
                currCardDisplayedIndex=0
            }
            allFlashcards = flashcardDatabase.getAllCards().toMutableList()
            val question= allFlashcards[currCardDisplayedIndex].question
            val answer= allFlashcards[currCardDisplayedIndex].answer
            flashcardQuestion.text=question
            flashcardAnswer.text=correctanswer //ans or correct ans
        }*/
    }
}