package minesweeper.domain.board

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Empty
import minesweeper.domain.cell.Mine
import minesweeper.domain.common.Position

class MineBoard(
    val board: Board,
    val mineIndices: List<Int>
) {
    val size = mineIndices.size
    lateinit var cells: List<Cell>
        private set

    init {
        build(mineIndices)
    }

    private fun build(mineIndices: List<Int>) {
        cells = List(board.size) {
            val x = it % board.width
            val y = it / board.width
            if (it in mineIndices) {
                Mine(Position(x, y))
            } else {
                Empty(Position(x, y))
            }
        }
    }
}

fun mineBoard(block: MineBoardBuilder.() -> Unit): MineBoard {
    return MineBoardBuilder().apply(block).build()
}
