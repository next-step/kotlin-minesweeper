package minesweeper.domain

import kotlin.random.Random

class MotherCells(
    private val width: Int,
    height: Int,
    private val source: CellSource = CellSource.Default(width, height)
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

    class Default(private val randomDoubles: RandomDoubles, private val matrix: Matrix) : CellSource {
        constructor(width: Int, height: Int) : this(
            RandomDoubles(width * height),
            Matrix(width, height)
        )

        override fun cells(bomb: Int): List<Cell> {
            val cells = makeCell(bomb)

            updateSide(cells)

            increaseCount(cells)

            return toCell(cells)
        }

        private fun makeCell(bomb: Int): List<MotherCell> {
            val boundary = boundary(bomb)
            return randomDoubles.map { MotherCell(it <= boundary) }
        }

        private fun boundary(count: Int) = randomDoubles.sorted().take(count).last()

        private fun increaseCount(cells: List<MotherCell>) {
            for (cell in cells) {
                cell.increaseCount()
            }
        }

        private fun updateSide(cells: List<MotherCell>) {
            for ((index, cell) in cells.withIndex()) {
                cell.sideCells = matrix.around(index).map { cells[it] }
            }
        }

        private fun toCell(cells: List<MotherCell>) = cells.map { it.cell }
    }
}

class RandomDoubles(private val values: List<Double>) {
    constructor(count: Int) : this(valuesOf(count))

    fun sorted(): List<Double> = values.sorted()

    fun map(mapper: (Double) -> MotherCell): List<MotherCell> {
        return values.map(mapper)
    }

    companion object {
        private fun valuesOf(count: Int) = (1..count).map { Random.nextDouble() }
    }
}
