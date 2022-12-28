package minesweeper.domain

import minesweeper.model.Point

class FakeMinGenerator(
    private val mines: List<Point>,
) : MineGenerator {

    override fun generate(mineCount: Int, height: Int, width: Int): List<Point> = mines
}
