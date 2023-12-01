package view

import domain.GameBoard
import domain.Position
import enum.CellStatus

class OutputView {
    fun displayBoard(board: GameBoard) {
        board.forEachCell { position, cellStatus ->
            print("${getDisplayChar(board, position, cellStatus)} ")
            if (position.x == board.boardWidth - 1) println()
        }
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
