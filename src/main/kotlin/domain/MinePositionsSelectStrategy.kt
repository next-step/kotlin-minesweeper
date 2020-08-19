package domain

import domain.block.Position

interface MinePositionsSelectStrategy {

    fun getMinePositionsFrom(positions: List<Position>, count: Int): List<Position>
}
