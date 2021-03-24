package minesweeper.domain

import kotlin.random.Random

class MotherCells(
    private val width: Int,
    private val height: Int,
    private val source: CellSource = CellSource.Default(width * height)
) {
    fun cells(bomb: Int): Cells {
        require(bomb > 0)

        return Cells(source.cells(bomb, Matrix(width, height)), width)
    }
}

interface CellSource {
    fun cells(bomb: Int, matrix: Matrix): List<Cell>

    class Default(private val randomDoubles: RandomDoubles) : CellSource {
        constructor(size: Int) : this(RandomDoubles(size))

        override fun cells(bomb: Int, matrix: Matrix): List<Cell> {
            val cells = makeCell(bomb)

            updateSide(cells, matrix)

            increaseCount(cells)

            val result = toCell(cells)
            val link = Link(matrix, result)
            result.forEachIndexed { index, cell ->
                cell.link = link.cells(index)
            }
            return result
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

        private fun updateSide(cells: List<MotherCell>, matrix: Matrix) {
            for ((index, cell) in cells.withIndex()) {
                cell.sideCells = matrix.around(index).map { cells[it] }
            }
        }

        private fun toCell(cells: List<MotherCell>) = cells.map { it.cell }
    }
}

class RandomDoubles(private val values: List<Double>) : List<Double> by values {
    constructor(count: Int) : this(valuesOf(count))

    companion object {
        private fun valuesOf(count: Int) = (1..count).map { Random.nextDouble() }
    }
}
