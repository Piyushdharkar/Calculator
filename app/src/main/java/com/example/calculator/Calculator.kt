package com.example.calculator

import java.util.*
import kotlin.collections.ArrayList


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
        token.toDoubleOrNull() ?: return false
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
        val stack = Stack<String>()

        for (token in expression) {
            if (isNumber(token)) {
                postfix.add(token)
            } else if (token == "(") {
                stack.push(token)
            } else if (token == ")") {
                while (stack.peek() != "(") {
                    postfix.add(stack.pop())
                }
                stack.pop()
            } else {
                if (stack.isEmpty()) {
                    stack.push(token)
                } else if (getWeight(stack.peek()) >= getWeight(token)) {
                    while (!stack.isEmpty() && getWeight(stack.peek()) >= getWeight(token)) {
                        postfix.add(stack.pop())
                    }
                    stack.push(token)
                } else {
                    stack.push(token)
                }
            }
        }

        while (!stack.isEmpty()) {
            postfix.add(stack.pop())
        }
    }

    private fun performOperation(a: Double, b: Double, operation: String): Double = when(operation) {
        "+" -> a + b
        "-" -> a - b
        "/" -> a / b
        "x" -> a * b
        else -> 0.0
    }

    private fun evaluation(): String {
        val stack = Stack<String>()

        for (token in postfix) {
            if (isNumber(token)) {
                stack.push(token)
            } else {
                val b = stack.pop().toDouble()
                val a = stack.pop().toDouble()
                stack.push(performOperation(a, b, token).toString())
            }
        }

        return stack.pop()
    }

    fun getAnswer(): String {
        val expression = getTokens()
        infixToPostfix(expression)

        return evaluation()
    }



}