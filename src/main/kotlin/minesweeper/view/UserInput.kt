package minesweeper.view

import java.io.InputStreamReader
import java.io.StringReader
import java.util.Scanner

interface UserInput<T> {
    fun answer(): T

    class Int(
        private val question: String,
        readable: Readable = InputStreamReader(System.`in`)
    ) : UserInput<kotlin.Int> {
        private val scanner: Scanner = Scanner(readable)

        constructor(question: String, answer: String) : this(question, StringReader(answer))

        override fun answer(): kotlin.Int {
            println(question)
            return scanner.nextInt()
        }
    }

    class IntArray(
        private val question: String,
        readable: Readable = InputStreamReader(System.`in`)
    ) : UserInput<kotlin.IntArray> {
        private val scanner: Scanner = Scanner(readable)

        constructor(question: String, answer: String) : this(question, StringReader(answer))

        override fun answer(): kotlin.IntArray {
            print(question)
            return scanner.nextLine()
                .split(",")
                .map { it.toInt() }
                .toIntArray()
        }
    }
}
