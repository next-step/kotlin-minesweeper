package minesweeper.domain

import minesweeper.model.Point

class FakeMinGenerator(
    private val mines: List<Point>,
) : MineGenerator {

    override fun generate(): List<Point> = mines
}
