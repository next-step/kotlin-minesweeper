package minesweeper.domain.cell

import minesweeper.domain.board.strategy.MineStrategy
import minesweeper.domain.common.NumberSet
import minesweeper.domain.common.Position
import minesweeper.domain.common.PositiveInt
import minesweeper.domain.common.div
import minesweeper.domain.common.rem

sealed class Cell(val position: Position)

class Cells private constructor(
    private val cells: List<Cell>,
    val mineIndices: NumberSet
) : List<Cell> by cells {

    companion object {
        fun of(width: PositiveInt, height: PositiveInt, numberOfMines: PositiveInt, mineStrategy: MineStrategy): Cells {
            val numberOfCells = width * height
            val mineIndices = mineStrategy.getMineIndices(numberOfCells, numberOfMines)

            val size = (width * height).value
            val cells = List(size) {
                val x = it % width
                val y = it / width
                if (it in mineIndices) {
                    Mine(Position.of(x, y))
                } else {
                    Empty(Position.of(x, y))
                }
            }
            return Cells(cells, mineIndices)
        }
    }
}
