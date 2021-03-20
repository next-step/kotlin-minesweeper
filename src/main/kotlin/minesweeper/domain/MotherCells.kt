package minesweeper.domain

import kotlin.random.Random

class MotherCells(private val width: Int, height: Int) {
    private val total = width * height

    init {
        require(width > 0 && height > 0)
    }

    fun cells(bomb: Int): Cells {
        require(bomb > 0)
        require(total > bomb)

        val randoms = (1..total).map { Random.nextDouble() }
        val boundary = randoms.sorted().take(bomb).last()
        return Cells(randoms.map { Cell(it <= boundary) }, width)
    }
}
