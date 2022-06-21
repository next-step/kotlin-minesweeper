package minesweeper

import minesweeper.domain.Board
import minesweeper.presentation.InputReceiver

object MineSweeper {

    fun run() {
        val boardSize = InputReceiver.receiveBoardSize()
        val mineCount = InputReceiver.receiveMineCount()

        Board.generate(boardSize, mineCount)
    }
}

fun main() {
    MineSweeper.run()
}
