package domain

import domain.strategy.MineCellGenerator

class Board(private val coordinate: Coordinate, private val mineCount: MineCount) {
    init {
        require(coordinate.height * coordinate.width >= mineCount.value) { INVALID_MINE_COUNT }
    }

    fun create(mineCellGenerator: MineCellGenerator) {
        val mineCells = mineCellGenerator.execute(coordinate, mineCount)
    }

    companion object {
        private const val INVALID_MINE_COUNT = "지뢰 개수는 전체 칸의 개수보다 작아야 합니다."
    }
}
