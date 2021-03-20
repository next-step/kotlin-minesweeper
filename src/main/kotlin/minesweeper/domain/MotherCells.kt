package minesweeper.domain

import kotlin.random.Random

class MotherCells(private val width: Int, height: Int) {
    private val total = width * height
    private val randoms = (1..total).map { Random.nextDouble() }

    init {
        require(width > 0 && height > 0)
    }

    fun cells(bomb: Int): Cells {
        require(bomb > 0)

        return Cells(randomCell(boundary(bomb)), width)
    }

    private fun randomCell(boundary: Double): List<Cell> = randoms.map { Cell(it <= boundary) }

    private fun boundary(bomb: Int) = randoms.sorted().take(bomb).last()
}
