package minesweeper.domain

import minesweeper.model.Point

class FakeMineDetector(
    private val mockPoint: Point,
    private val mockSurroundMineCount: Int,
) : MineDetector {

    override fun detect(point: Point, minePoints: List<Point>): Int =
        if (mockPoint == point) mockSurroundMineCount else 0
}
