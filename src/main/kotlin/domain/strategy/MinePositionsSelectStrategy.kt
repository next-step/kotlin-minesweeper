package domain.strategy

import domain.MineCount
import domain.Position

interface MinePositionsSelectStrategy {
    fun getMinePositions(positions: List<Position>, mineCount: MineCount): List<Position>
}
