package minesweeper.domain

import minesweeper.model.Height
import minesweeper.model.Point
import minesweeper.model.Width

interface MineDetector {
    val mapWidth: Width
    val mapHeight: Height

    fun detect(point: Point, minePoints: List<Point>): Int
}

class BlockMineDetector(
    override val mapWidth: Width,
    override val mapHeight: Height
) : MineDetector {

    override fun detect(point: Point, minePoints: List<Point>): Int =
        countSurroundMines(point, minePoints = minePoints)

    private fun countSurroundMines(point: Point, minePoints: List<Point>): Int {
        var count = 0
        for (x in point.x - 1..point.y + 1) {
            for (y in point.y - 1..point.y + 1) {
                if (x < 0 || x >= mapHeight.value) continue
                if (y < 0 || y >= mapWidth.value) continue
                if (minePoints.contains(Point(x, y))) count++
            }
        }
        return count
    }
}
