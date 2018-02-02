package com.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private fun getChar(view: View) = when (view.id) {
        R.id.oneButton -> "1"
        R.id.twoButton -> "2"
        R.id.threeButton -> "3"
        R.id.fourButton -> "4"
        R.id.fiveButton -> "5"
        R.id.sixButton -> "6"
        R.id.sevenButton -> "7"
        R.id.eightButton -> "8"
        R.id.nineButton -> "9"
        R.id.zeroButton -> "0"
        R.id.dotButton -> "."

        R.id.leftBracketButton -> "("
        R.id.rightBracketButton -> ")"

        R.id.plus -> "+"
        R.id.minus -> "-"
        R.id.multiply -> "x"
        R.id.divide -> "/"
        R.id.equalTo -> "="

        else -> "Error"
    }


    private fun setButtonOnClickListeners() {
        val addCharToTextView = {view: View -> textView.text = textView.text.toString() + getChar(view)}

        oneButton.setOnClickListener(addCharToTextView)
        twoButton.setOnClickListener(addCharToTextView)
        threeButton.setOnClickListener(addCharToTextView)
        fourButton.setOnClickListener(addCharToTextView)
        fiveButton.setOnClickListener(addCharToTextView)
        sixButton.setOnClickListener(addCharToTextView)
        sevenButton.setOnClickListener(addCharToTextView)
        eightButton.setOnClickListener(addCharToTextView)
        nineButton.setOnClickListener(addCharToTextView)
        zeroButton.setOnClickListener(addCharToTextView)
        dotButton.setOnClickListener(addCharToTextView)

        leftBracketButton.setOnClickListener(addCharToTextView)
        rightBracketButton.setOnClickListener(addCharToTextView)

        plus.setOnClickListener(addCharToTextView)
        minus.setOnClickListener(addCharToTextView)
        multiply.setOnClickListener(addCharToTextView)
        divide.setOnClickListener(addCharToTextView)

        try {
            equalTo.setOnClickListener({ textView.text = Calculator(textView.text.toString()).getAnswer() })
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Invalid expression", Toast.LENGTH_SHORT).show()
        }
        clearButton.setOnClickListener({textView.text = ""})
        backspaceButton.setOnClickListener({textView.text = if (textView.text != "")
                textView.text.toString().substring(0, textView.text.toString().length - 1)
        else
            ""})

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setButtonOnClickListeners()

    }
}
