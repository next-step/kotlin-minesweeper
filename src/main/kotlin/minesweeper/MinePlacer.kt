package minesweeper

import kotlin.random.Random

class MinePlacer(
    private val dimensions: Dimensions,
    private val mineCount: Int,
) {
    init {
        require(mineCount in 1 until dimensions.totalCells) {
            "지뢰 개수는 전체 칸 수보다 적고 0보다 커야 합니다."
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
