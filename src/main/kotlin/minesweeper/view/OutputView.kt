package minesweeper.view

import minesweeper.model.Board
import minesweeper.model.Column
import minesweeper.model.Height
import minesweeper.model.Position
import minesweeper.model.Row
import minesweeper.model.Width
import minesweeper.state.Finished
import minesweeper.state.State
import minesweeper.view.resource.getString

class OutputView {

    fun showGamePlay() {
        println("지뢰찾기 게임 시작")
    }

    private fun onRow(height: Height, block: (Row) -> Unit) =
        repeat(height.value) { index -> block(Row(index + 1)) }

    private fun onColumn(width: Width, block: (Column) -> Unit) =
        repeat(width.value) { index -> block(Column(index + 1)) }

    fun showGameState(state: State) {
        if (state.isFinished()) {
            when (state as Finished) {
                is Finished.Win -> println("Win Game.")
                is Finished.Lose -> println("Lose Game.")
            }
        } else {
            showBoard(state.board)
        }
    }

    private fun showBoard(board: Board) = onRow(board.height) { row ->
        onColumn(board.width) { column ->
            val position = Position.of(row, column)
            print(getString(board.cells[position]))
            print(" ")
        }
        println()
    }
}
