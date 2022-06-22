package minesweeper.domain.cell

import minesweeper.domain.board.strategy.MineStrategy

sealed class Cell(val position: Position)

class Cells private constructor(
    private val cells: List<Cell>,
    val mineIndices: List<Int>
) : List<Cell> by cells {

    companion object {
        fun of(width: Int, height: Int, numberOfMines: Int, mineStrategy: MineStrategy): Cells {
            val numberOfCells = width * height
            val mineIndices = mineStrategy.getMineIndices(numberOfCells, numberOfMines)
            val size = width * height

            return createCells(size, width, mineIndices)
        }

        private fun createCells(size: Int, width: Int, mineIndices: List<Int>): Cells {
            val cells = List(size) {
                val x = it % width
                val y = it / width
                if (it in mineIndices) {
                    Mine(Position(x, y))
                } else {
                    Empty(Position(x, y))
                }
            }
            return Cells(cells, mineIndices)
        }
    }
}
