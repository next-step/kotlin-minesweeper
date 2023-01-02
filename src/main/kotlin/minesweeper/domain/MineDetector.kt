package minesweeper.domain

import minesweeper.model.Height
import minesweeper.model.Point
import minesweeper.model.Width

interface MineDetector {
    fun detect(point: Point, minePoints: List<Point>): Int
}

class BlockMineDetector(
    private val mapWidth: Width,
    private val mapHeight: Height,
) : MineDetector {

    override fun detect(point: Point, minePoints: List<Point>): Int =
        countSurroundMines(point, minePoints = minePoints)

    private fun countSurroundMines(point: Point, minePoints: List<Point>): Int =
        PointDirections.neighbors(point, Point(mapHeight.value, mapWidth.value))
            .count { minePoints.contains(it) }
}
