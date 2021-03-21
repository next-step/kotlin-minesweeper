package minesweeper.domain

import kotlin.random.Random

class MotherCells(
    private val width: Int,
    height: Int,
    private val source: CellSource = CellSource.Default(width * height)
) {
    init {
        require(width > 0 && height > 0)
    }

    fun cells(bomb: Int): Cells {
        require(bomb > 0)

        return Cells(source.cells(bomb), width)
    }
}

interface CellSource {
    fun cells(bomb: Int): List<Cell>

    class Default(private val randoms: List<Double>) : CellSource {
        constructor(total: Int) : this((1..total).map { Random.nextDouble() })

        override fun cells(bomb: Int): List<Cell> {
            val boundary = boundary(bomb)

            val cells = randoms.map { MotherCell(it <= boundary) }
            for ((index, cell) in cells.withIndex()) {
                cell.sideCells = Coordinate(index, Matrix(2, 2)).sideIndexes.map { cells[it] }
            }

            for (cell in cells) {
                cell.increaseCount()
            }

            return cells.map { it.cell }
        }

        private fun boundary(bomb: Int) = randoms.sorted().take(bomb).last()
    }
}
