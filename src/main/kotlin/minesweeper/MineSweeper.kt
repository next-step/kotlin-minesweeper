package minesweeper

import minesweeper.presentation.InputReceiver

object MineSweeper {

    fun run() {
        val boardSize = InputReceiver.receiveBoardSize()
    }
}

fun main() {
    MineSweeper.run()
}
