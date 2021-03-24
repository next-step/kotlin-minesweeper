package minesweeper.domain

import kotlin.random.Random

class CellProduction(
    private val width: Int,
    private val height: Int,
    private val budding: Budding = Budding.Default(width * height)
) {
    fun cells(bomb: Int): Cells {
        require(bomb > 0)

        return budding.cells(bomb, Matrix(width, height))
    }
}

interface Budding {
    fun cells(bomb: Int, matrix: Matrix): Cells

    class Default(private val randomDoubles: RandomDoubles) : Budding {
        constructor(size: Int) : this(RandomDoubles(size))

        override fun cells(bomb: Int, matrix: Matrix): Cells {
            val stemCell = stemCell(bomb)

            stemCell.updateSide(matrix)

            stemCell.increaseCount()

            val cells = stemCell.cell()

            val link = Link(matrix, cells)
            cells.forEachIndexed { index, cell ->
                cell.link = link.cells(index)
            }
            return Cells(cells, matrix)
        }

        private fun stemCell(bomb: Int): StemCell {
            val boundary = boundary(bomb)
            return StemCell(randomDoubles.map { ProtoCell(it <= boundary) })
        }

        private fun boundary(count: Int) = randomDoubles.sorted().take(count).last()
    }
}

class StemCell(private val protoCells: List<ProtoCell>) {
    fun updateSide(matrix: Matrix) {
        for ((index, cell) in protoCells.withIndex()) {
            cell.sideCells = matrix.around(index).map { protoCells[it] }
        }
    }

    fun increaseCount() {
        for (cell in protoCells) {
            cell.increaseCount()
        }
    }

    fun cell() = protoCells.map { it.cell }
}

class RandomDoubles(private val values: List<Double>) : List<Double> by values {
    constructor(count: Int) : this(valuesOf(count))

    companion object {
        private fun valuesOf(count: Int) = (1..count).map { Random.nextDouble() }
    }
}
