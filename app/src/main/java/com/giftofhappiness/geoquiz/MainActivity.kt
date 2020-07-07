package com.giftofhappiness.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*



private const val TAG = "MainActivity"



class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: View


    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "oncreate(Bundle?)called")
        setContentView(R.layout.activity_main)




//         val quizViewModel: QuizViewModel by viewModels()
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)







        trueButton.setOnClickListener{view: View ->
            checkAnswer(true)

        }
        falseButton.setOnClickListener{view: View ->
            checkAnswer(false)

        }

            questionUpdate()


    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called.")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onstop called.")



    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onpause called.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "ondestroy called.")
    }


    fun onNextButtonClicked(view: View){

//      currentIndex =  (currentIndex +1) % questionBank.size
        quizViewModel.moveToNext()
       questionUpdate()

    }


    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if(userAnswer==correctAnswer) {

            R.string.correct_toast
        }else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId,Toast.LENGTH_SHORT).show()
    }

    private fun questionUpdate(){
//        val questionTextView =questionBank[currentIndex].textResID
        val questionTextResId = quizViewModel.currentQuestionText
        question_text_view.setText(questionTextResId)



    }




}