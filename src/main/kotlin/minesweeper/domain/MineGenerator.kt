package minesweeper.domain

import kotlin.random.Random

class MineGenerator(private val gameBoard: GameBoard, private val mineCount: Int) {
    fun generateRandomPoints(): List<Point> {
        val minePoints = mutableSetOf<Point>()

        while (minePoints.size < mineCount) {
            val randomPoint = Point(Random.nextInt(gameBoard.height), Random.nextInt(gameBoard.width))
            minePoints.add(randomPoint)
        }

        return minePoints.toList()
    }
}
