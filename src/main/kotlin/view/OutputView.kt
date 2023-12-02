package view

import domain.GameBoard
import domain.Position
import enum.CellStatus

class OutputView {
    fun displayBoard(board: GameBoard) {
        board.processEachCell { position, cellStatus ->
            printCell(board, position, cellStatus)
            if (position.x == board.width - 1) println()
        }
    }

    private fun printCell(board: GameBoard, position: Position, cellStatus: CellStatus) {
        val displayChar = getDisplayChar(board, position, cellStatus)
        print("$displayChar ")
    }

    private fun getDisplayChar(board: GameBoard, position: Position, cellStatus: CellStatus): String {
        return when {
            cellStatus == CellStatus.MINE -> "*"
            else -> getMineCountDisplay(board, position)
        }
    }

    private fun getMineCountDisplay(board: GameBoard, position: Position): String {
        val mineCount = board.countMinesAround(position)
        return if (mineCount > 0) mineCount.toString() else "0"
    }
}
