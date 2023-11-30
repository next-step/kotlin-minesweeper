package domain.strategyImpl

import domain.BoardSettings
import domain.strategy.CreatePointStrategy

class RandomPointFactory: CreatePointStrategy {
    override fun createMinePoints(boardSettings: BoardSettings): List<Int> {
        val minePoints = mutableListOf<Int>()
        while (minePoints.size < boardSettings.mineCount) {
            val randomPoint = (0 until boardSettings.row * boardSettings.col).random()
            if (randomPoint !in minePoints) {
                minePoints.add(randomPoint)
            }
        }
        return minePoints
    }
}
