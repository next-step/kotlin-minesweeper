package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.Position

class MineBoard private constructor(val cells: Cells) {

    fun open(position: Position): BoardStatus {
        val boardStatus = cells.open(position)
        return if (cells.hasClosedEmptyCell()) {
            boardStatus
        } else {
            BoardStatus.CLEAR
        }
    }

    companion object {
        fun of(
            width: Int,
            height: Int,
            numberOfMines: Int,
            mineMaker: MineMaker
        ): MineBoard {
            val size = width * height

            require(width >= 0 && height >= 0) { "property must be zero or positive." }
            require(numberOfMines in (0..size)) { "number of mines must be within range of 0 ~ $size" }

            val cells = Cells.of(width, height, numberOfMines, mineMaker)
            NearbyMineCounter.count(cells)
            return MineBoard(cells)
        }
    }
}

fun mineBoard(block: MineBoardBuilder.() -> Unit): MineBoard {
    return MineBoardBuilder().apply(block).build()
}
