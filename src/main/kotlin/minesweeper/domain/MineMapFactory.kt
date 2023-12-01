package minesweeper.domain

interface MineMapFactory {
    fun create(
        minePositions: Positions,
        emptyPositions: Positions
    ): MineMap
}
