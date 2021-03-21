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

    class Default(private val randomDoubles: RandomDoubles, private val coordinate: Coordinate) : CellSource {
        constructor(width: Int, height: Int) : this(
            RandomDoubles(width * height),
            Coordinate(Matrix(width, height))
        )

        override fun cells(bomb: Int): List<Cell> {
            val boundary = boundary(bomb)

            val cells = randomDoubles.map { MotherCell(it <= boundary) }

            for ((index, cell) in cells.withIndex()) {
                cell.sideCells = coordinate.sideIndexes(index).map { cells[it] }
            }

            for (cell in cells) {
                cell.increaseCount()
            }

            return cells.map { it.cell }
        }

        private fun boundary(bomb: Int) = randomDoubles.sorted().take(bomb).last()
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
