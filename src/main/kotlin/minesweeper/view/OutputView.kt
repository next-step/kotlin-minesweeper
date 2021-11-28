package minesweeper.view

import minesweeper.model.Board
import minesweeper.model.Cell
import minesweeper.model.Column
import minesweeper.model.Height
import minesweeper.model.Position
import minesweeper.model.Row
import minesweeper.model.Width

class OutputView {

    fun showGamePlay(board: Board) {
        println("지뢰찾기 게임 시작")
        printBoard(board)
    }

    private fun printBoard(board: Board) = onRow(board.height) { row ->
        onColumn(board.width) { column ->
            val position = Position(row, column)
            printCell(board.cells[position])
            print(" ")
        }
        println()
    }

    private fun onRow(height: Height, block: (Row) -> Unit) {
        repeat(height.value) { row -> block(Row(row + 1)) }
    }

    private fun onColumn(width: Width, block: (Column) -> Unit) {
        repeat(width.value) { column -> block(Column(column + 1)) }
    }

    private fun printCell(cell: Cell?) {
        when (cell) {
            is Cell.Blank -> print("C")
            is Cell.Mine -> print("*")
        }
    }
}
