package com.example.calculator

import java.util.*
import kotlin.collections.ArrayList
import java.nio.file.Files.size




class Calculator(private val infix: String) {

    private val postfix = ArrayList<String>()


    private fun getWeight(token: String) = when(token) {
        "(" -> 0
        ")" -> 0
        "+" -> 1
        "-" -> 1
        "x" -> 2
        "/" -> 2
        else -> 0
    }

    private fun isNumber(token: String): Boolean {
        token.toFloatOrNull() ?: return false
        return true
    }

    private fun getTokens(): ArrayList<String> {
        val tokens = ArrayList<String>()
        tokens.add(Character.toString(infix[0]))

        for (i in 1 until infix.length) {
            val ch = infix[i]
            val lch = infix[i - 1]

            if ((Character.isDigit(ch) || ch == '.') && (Character.isDigit(lch) || lch == '.')) {
                val lastIndex = tokens.size - 1
                tokens[lastIndex] = tokens[lastIndex] + ch
            } else {
                tokens.add(Character.toString(ch))
            }
        }
        return tokens
    }

    private fun infixToPostfix(expression: ArrayList<String>) {

    }

    fun getAnswer(): String {
        val expression = getTokens()
        infixToPostfix(expression)


        return postfix.toString()
    }




}