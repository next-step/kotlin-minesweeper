package minesweeper

import kotlin.random.Random

class MinePlacer(
    private val dimensions: Dimensions,
    private val mineCount: Int,
) {
    init {
        require(mineCount in 1 until dimensions.totalCells) {
            "Mine count must be less than total cells and greater than 0."
        }
    }

    fun placeMines(): Set<Position> {
        val positions = mutableSetOf<Position>()
        while (positions.size < mineCount) {
            val x = Random.nextInt(dimensions.width)
            val y = Random.nextInt(dimensions.height)
            positions.add(Position(x, y))
        }
        return positions
    }
}
