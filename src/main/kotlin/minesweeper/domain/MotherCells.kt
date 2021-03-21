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
    val total: Int
    fun cells(bomb: Int): List<Cell>

    class Default(override val total: Int) : CellSource {
        private val randoms = (1..total).map { Random.nextDouble() }

        override fun cells(bomb: Int): List<Cell> {
            val boundary = boundary(bomb)
            return randoms.map { Cell(it <= boundary) }
        }

        private fun boundary(bomb: Int) = randoms.sorted().take(bomb).last()
    }
}
