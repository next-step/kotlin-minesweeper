package minesweeper.domain.board

import minesweeper.domain.board.strategy.MineStrategy
import minesweeper.domain.cell.Cells
import minesweeper.domain.common.PositiveInt
import minesweeper.domain.common.contains
import minesweeper.domain.common.rangeTo

class MineBoard private constructor(
    val width: PositiveInt,
    val height: PositiveInt,
    numberOfMines: PositiveInt,
    mineStrategy: MineStrategy
) {
    var cells: Cells

    val size get() = width * height

    init {
        require(numberOfMines in (0..size)) { "number of mines must be within range of 0 ~ $size" }
        cells = Cells.of(width, height, numberOfMines, mineStrategy)
        NearbyMineCounter.count(this)
    }

    companion object {
        fun of(
            width: PositiveInt,
            height: PositiveInt,
            numberOfMines: PositiveInt,
            mineStrategy: MineStrategy
        ): MineBoard {
            return MineBoard(width, height, numberOfMines, mineStrategy)
        }
    }
}

fun mineBoard(block: MineBoardBuilder.() -> Unit): MineBoard {
    return MineBoardBuilder().apply(block).build()
}
