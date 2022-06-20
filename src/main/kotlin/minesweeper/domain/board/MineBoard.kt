package minesweeper.domain.board

import minesweeper.domain.cell.CellList
import minesweeper.domain.cell.Empty
import minesweeper.domain.cell.Mine
import minesweeper.domain.common.NumberSet
import minesweeper.domain.common.Position

class MineBoard(
    val board: Board,
    val mineIndices: NumberSet
) {
    lateinit var cells: CellList
        private set

    init {
        build(mineIndices)
    }

    private fun build(mineIndices: NumberSet) {
        cells = CellList(
            List(board.size) {
                val x = it % board.width
                val y = it / board.width
                if (it in mineIndices) {
                    Mine(Position(x, y))
                } else {
                    Empty(Position(x, y))
                }
            }
        )
    }
}

fun mineBoard(block: MineBoardBuilder.() -> Unit): MineBoard {
    return MineBoardBuilder().apply(block).build()
}
