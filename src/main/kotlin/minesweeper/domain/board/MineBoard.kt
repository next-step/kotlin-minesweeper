package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.Empty
import minesweeper.domain.cell.Mine
import minesweeper.domain.common.NumberSet
import minesweeper.domain.common.Position
import minesweeper.domain.common.PositiveInt
import minesweeper.domain.common.contains
import minesweeper.domain.common.div
import minesweeper.domain.common.rangeTo
import minesweeper.domain.common.rem

class MineBoard(
    width: PositiveInt,
    height: PositiveInt,
    numberOfMines: PositiveInt,
    randomMineStrategy: (numberOfCells: PositiveInt, numberOfMines: PositiveInt) -> List<Int>
) {
    var mineIndices: NumberSet
    lateinit var board: Board
        private set

    init {
        val size = width * height
        require(numberOfMines in (0..size)) { "number of mines must be within range of 0 ~ $size" }
        mineIndices = NumberSet.of(randomMineStrategy(size, numberOfMines))
        buildBoard(width, height, mineIndices)
    }

    private fun buildBoard(width: PositiveInt, height: PositiveInt, mineIndices: NumberSet) {
        val size = (width * height).value
        val cells = Cells(
            List(size) {
                val x = it % width
                val y = it / width
                if (it in mineIndices) {
                    Mine(Position.of(x, y))
                } else {
                    Empty(Position.of(x, y))
                }
            }
        )
        board = Board.of(width, height, cells)
    }
}

fun mineBoard(block: MineBoardBuilder.() -> Unit): MineBoard {
    return MineBoardBuilder().apply(block).build()
}
