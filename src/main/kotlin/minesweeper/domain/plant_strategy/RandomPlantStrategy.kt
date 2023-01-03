package minesweeper.domain.plant_strategy

import minesweeper.domain.Point
import java.util.*

class RandomPlantStrategy : PlantStrategy {
    override fun createMines(width: Int, height: Int, mineCount: Int): Set<Point> {
        val minePoints = mutableSetOf<Point>()
        while (minePoints.size < mineCount) {
            val currentWidth = Random().nextInt(width)
            val currentHeight = Random().nextInt(height)
            minePoints.add(Point(currentWidth, currentHeight))
        }
        return minePoints
    }
}
