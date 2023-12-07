package view

import domain.GameBoard
import domain.Position
import enum.CellStatus

class OutputView {
    fun displayBoard(gameBoard: GameBoard) {
        val width = gameBoard.boardWidth
        gameBoard.processEachCell { position, cellStatus ->
            printCell(gameBoard, position, cellStatus)
            if (position.x == width - 1) println()
        }
    }

    private fun printCell(gameBoard: GameBoard, position: Position, cellStatus: CellStatus) {
        val displayChar = when (cellStatus) {
            CellStatus.OPEN -> gameBoard.countMinesAround(position).toString()
            CellStatus.CLOSED -> "C"
            CellStatus.MINE -> "*"
        }
        print("$displayChar ")
    }

    fun displayGameOverMessage() {
        println("Lose Game.")
    }
}
