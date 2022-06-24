package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.BoardOpenResult
import minesweeper.presentation.InputReceiver
import minesweeper.presentation.UI

object MineSweeper {

    fun run() {
        val boardSize = InputReceiver.receiveBoardSize()
        val mineCount = InputReceiver.receiveMineCount()

        val board = Board(boardSize, mineCount)

        UI.drawStartMessage()
        UI.drawBoard(board)

        play(board)
    }

    private fun play(board: Board) {
        do {
            val result = board.open(InputReceiver.receiveOpenCoordinate())

            val isGameOver = result == BoardOpenResult.Fail
            val isCompleted = board.isCompleted()
            val canNextTurn = !isCompleted && !isGameOver

            drawTurn(board, result)
        } while (canNextTurn)
    }

    private fun drawTurn(board: Board, result: BoardOpenResult) {
        when (result) {
            BoardOpenResult.Success -> {
                if (board.isCompleted()) {
                    drawFinalBoard(board)
                    UI.drawWinMessage()
                } else {
                    UI.drawBoard(board)
                }
            }
            BoardOpenResult.Fail -> {
                drawFinalBoard(board)
                UI.drawLoseMessage()
            }
            BoardOpenResult.AlreadyOpened -> UI.drawAlreadyOpenedMessage()
            BoardOpenResult.NotFound -> UI.drawNotFoundMessage()
        }
    }

    private fun drawFinalBoard(board: Board) {
        board.openAllMine()
        UI.drawBoard(board)
    }
}

fun main() {
    MineSweeper.run()
}
