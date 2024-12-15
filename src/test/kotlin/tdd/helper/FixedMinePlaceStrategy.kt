package tdd.helper

import tdd.minesweeper.domain.strategy.MinePlaceStrategy

class FixedMinePlaceStrategy(
    private val minePlaces: List<Pair<Int, Int>>,
) : MinePlaceStrategy {
    override fun calcMinePlace(height: Int, width: Int, mineCount: Int): List<Pair<Int, Int>> {
        return minePlaces
    }
}
