package minesweeper.domain.block

import minesweeper.exception.NotCalculateAdjacentMineCountException

data class MineBlock(override val position: Position) : Block(position) {
    override val isMine: Boolean = true

    override val adjacentMineCount: AdjacentMineCount get() = throw NotCalculateAdjacentMineCountException(this::class.java.toString())
}
