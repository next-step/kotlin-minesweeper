package minesweeper.domain

import minesweeper.model.Height
import minesweeper.model.MineCount
import minesweeper.model.Point
import minesweeper.model.Width

class FakeMineGenerator(
    private val mines: List<Point>,
) : MineGenerator {

    override fun generate(mineCount: MineCount, height: Height, width: Width): List<Point> = mines
}
