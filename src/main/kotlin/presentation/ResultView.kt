package presentation

import domain.Board
import domain.Space

object ResultView {
    fun printBoard(board: Board) {
        for (rowIndex in 0 until board.height) {
            printRow(board, rowIndex)
        }
    }

    private fun printRow(board: Board, rowIndex: Int) {
        val row = getRow(board, rowIndex)
        for (columnIndex in 0 until board.width) {
            printSpace(row[columnIndex])
        }
        println()
    }

    private fun getRow(board: Board, rowIndex: Int): List<Space> {
        return board.board().subList(rowIndex * board.height, (rowIndex + 1) * board.height)
    }

    private fun printSpace(space: Space) {
        when (space) {
            is Space.Mine -> print("*")
            is Space.Empty -> print("C")
        }
        print(" ")
    }
}
