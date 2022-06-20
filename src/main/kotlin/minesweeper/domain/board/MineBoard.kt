package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.Empty
import minesweeper.domain.cell.Mine
import minesweeper.domain.common.NumberSet
import minesweeper.domain.common.Position
import minesweeper.domain.common.Rectangle
import minesweeper.domain.common.div
import minesweeper.domain.common.rem

class MineBoard(
    board: Board,
    numberOfMines: Int,
    randomMineStrategy: (numberOfCells: Int, numberOfMines: Int) -> List<Int>
) : Rectangle by board {
    var mineIndices: NumberSet
    lateinit var cells: Cells
        private set

    init {
        require(numberOfMines in (0..board.size)) { "number of mines must be within range of 0 ~ ${board.size}" }
        mineIndices = NumberSet.of(randomMineStrategy(board.size, numberOfMines))
        build(mineIndices)
    }

    private fun build(mineIndices: NumberSet) {
        cells = Cells(
            List(this.size) {
                val x = it % this.width
                val y = it / this.width
                if (it in mineIndices) {
                    Mine(Position.of(x, y))
                } else {
                    Empty(Position.of(x, y))
                }
            }
        )
    }
}

fun mineBoard(block: MineBoardBuilder.() -> Unit): MineBoard {
    return MineBoardBuilder().apply(block).build()
}
