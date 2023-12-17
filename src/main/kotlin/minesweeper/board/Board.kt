package minesweeper.board

import minesweeper.cell.Cell

@JvmInline
value class Board(
    private val board: List<List<Cell>>
) {

    operator fun get(index: Int) = this.board[index]
}
