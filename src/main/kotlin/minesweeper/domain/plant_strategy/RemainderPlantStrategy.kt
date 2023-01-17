package minesweeper.domain.plant_strategy

import minesweeper.domain.Point

class RemainderPlantStrategy : PlantStrategy {

    override fun createMines(width: Int, height: Int, mineCount: Int): Set<Point> {
        val minePoints = mutableSetOf<Point>()

        if (mineCount == 0) return minePoints

        val term = width * height / mineCount
        val maxNumber = term * mineCount
        for (i: Int in 0 until maxNumber step term) {
            val currentWidth = i % width
            val currentHeight = i / width

            minePoints.add(Point(currentWidth, currentHeight))
        }

        return minePoints
    }
}
