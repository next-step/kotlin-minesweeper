package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.BoardGenerator
import minesweeper.domain.BoardOpenResult
import minesweeper.presentation.InputReceiver
import minesweeper.presentation.UI

object MineSweeper {

    fun run() {
        val boardSize = InputReceiver.receiveBoardSize()
        val mineCount = InputReceiver.receiveMineCount()

        val board = BoardGenerator().generate(boardSize, mineCount)

        UI.drawStartMessage()
        UI.drawBoard(board)

        play(board)
    }

    private fun play(board: Board) {
        do {
            val result = board.open(InputReceiver.receiveOpenCoordinate())
            val isGameOver = result == BoardOpenResult.Fail
            val isCompleted = board.isCompleted()

            UI.drawBoard(board)

            if (isGameOver) UI.drawLoseMessage()
            else if (isCompleted) UI.drawWinMessage()
        } while (!isGameOver || !isCompleted)
    }
}

fun main() {
    MineSweeper.run()
}
