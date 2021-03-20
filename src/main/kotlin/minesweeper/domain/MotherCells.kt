package minesweeper.domain

import kotlin.random.Random

class MotherCells(private val width: Int, private val height: Int) {
    fun cells(bomb: Int): Cells {
        val randoms = (1..(width * height)).map { Random.nextDouble() }
        val boundary = randoms.sorted().take(bomb + 1).last()
        return Cells(randoms.map { Cell(it < boundary) }, width)
    }
}
