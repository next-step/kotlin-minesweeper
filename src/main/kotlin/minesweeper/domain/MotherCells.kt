package minesweeper.domain

import kotlin.random.Random

class MotherCells(private val width: Int, private val height: Int) {
    init {
        require(width > 0 && height > 0)
    }

    fun cells(bomb: Int): Cells {
        require(bomb > 0)
        val randoms = (1..(width * height)).map { Random.nextDouble() }
        val boundary = randoms.sorted().take(bomb).last()
        return Cells(randoms.map { Cell(it <= boundary) }, width)
    }
}
