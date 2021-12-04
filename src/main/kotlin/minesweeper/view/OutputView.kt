package minesweeper.view

import minesweeper.model.Board
import minesweeper.model.Column
import minesweeper.model.Height
import minesweeper.model.Position
import minesweeper.model.Row
import minesweeper.model.Width
import minesweeper.view.resource.getString

class OutputView {

    fun showGamePlay(board: Board) {
        println("지뢰찾기 게임 시작")
        printBoard(board)
    }

    private fun printBoard(board: Board) = onRow(board.height) { row ->
        onColumn(board.width) { column ->
            val position = Position(row, column)
            print(getString(board.cells[position]))
            print(" ")
        }
        println()
    }

    private fun onRow(height: Height, block: (Row) -> Unit) =
        repeat(height.value) { index -> block(Row(index + 1)) }

    private fun onColumn(width: Width, block: (Column) -> Unit) =
        repeat(width.value) { index -> block(Column(index + 1)) }
}
