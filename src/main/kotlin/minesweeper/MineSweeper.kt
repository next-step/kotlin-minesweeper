package minesweeper

import minesweeper.domain.Board
import minesweeper.presentation.InputReceiver
import minesweeper.presentation.UI

object MineSweeper {

    fun run() {
        val boardSize = InputReceiver.receiveBoardSize()
        val mineCount = InputReceiver.receiveMineCount()

        val board = Board.generate(boardSize, mineCount)

        UI.drawStartMessage()
        UI.drawBoard(board)
    }
}

fun main() {
    MineSweeper.run()
}
