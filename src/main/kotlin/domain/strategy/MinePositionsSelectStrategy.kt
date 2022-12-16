package domain.strategy

import domain.Position

interface MinePositionsSelectStrategy {
    fun getMinePositions(positions: List<Position>, mineCount: Int): List<Position>
}
